import { z } from 'zod';

import AbstractStorage from '@/classes/Abstract.storage';

const schema = z.enum(['en', 'fr']);

export const LanguageChoices = schema.enum;
export type Language = z.infer<typeof schema>;

class LanguageStorage extends AbstractStorage<typeof schema> {
  constructor() {
    super('lang', schema, LanguageStorage.getDefaultLanguage());
  }

  private static getDefaultLanguage() {
    if (navigator.language.startsWith('fr')) return LanguageChoices.fr;
    return LanguageChoices.en;
  }
}

export default new LanguageStorage();
