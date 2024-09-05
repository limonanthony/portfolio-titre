import enJson from '@/lang/en.json';
import frJson from '@/lang/fr.json';
import { Language, LanguageChoices } from '@/services/storages/Language.storage';

export default class LangUtils {
  public static getTranslationFile(language: Language) {
    if (language === LanguageChoices.fr) return frJson;
    return enJson;
  }
}
