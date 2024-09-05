import { ZodSchema, z } from 'zod';

type GenericType<T extends ZodSchema> = z.infer<T>;

export default abstract class AbstractStorage<SchemaType extends ZodSchema> {
  private readonly key: string;

  private schema: SchemaType;

  protected constructor(key: string, schema: SchemaType, defaultValue?: GenericType<SchemaType>) {
    this.key = key;
    this.schema = schema;
    if (!this.exists()) this.set(defaultValue);
  }

  private getSafeValue(): GenericType<SchemaType> | null {
    const value = localStorage.getItem(this.key);
    if (value === null) return null;
    const safeParsedValue = this.schema.safeParse(JSON.parse(value));
    if (!safeParsedValue.success) {
      this.remove();
      return null;
    }
    return safeParsedValue.data;
  }

  public exists(): boolean {
    return !!localStorage.getItem(this.key);
  }

  public get(): GenericType<SchemaType> | null;

  public get(defaultValue?: GenericType<SchemaType>): GenericType<SchemaType>;

  public get(defaultValue?: GenericType<SchemaType>): GenericType<SchemaType> | null {
    const value = this.getSafeValue();
    if (value === null && defaultValue) return defaultValue;
    return value;
  }

  public remove() {
    localStorage.removeItem(this.key);
  }

  public set(newValue: GenericType<SchemaType>): void {
    const parsedValue = this.schema.safeParse(newValue);
    if (parsedValue.success) localStorage.setItem(this.key, JSON.stringify(parsedValue.data));
    else if (import.meta.env.DEV) console.warn(new Error('Failed to save the value to the local-storage'));
  }
}
