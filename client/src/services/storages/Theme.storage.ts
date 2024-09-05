import { z } from 'zod';

import AbstractStorage from '@/classes/Abstract.storage.ts';

const schema = z.enum(['light', 'dark']);

export const ThemeChoices = schema.enum;
export type Theme = z.infer<typeof schema>;

class ThemeStorage extends AbstractStorage<typeof schema> {
  constructor() {
    super('theme', schema);
  }
}

export default new ThemeStorage();
