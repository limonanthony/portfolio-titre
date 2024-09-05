export type Arguments = Array<Record<string, boolean> | null | string | undefined>;

export default class ClassUtils {
  public static concat(...args: Arguments): string {
    return args
      .flatMap((arg): string[] => {
        if (!arg) return [];
        if (typeof arg === 'string') return [arg];
        return Object.entries(arg)
          .filter(([_, isOk]) => isOk)
          .map(([className, _]) => className);
      })
      .join(' ');
  }
}
