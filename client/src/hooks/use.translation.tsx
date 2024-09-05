import { useEffect, useState } from 'react';

import { useLanguageSelector } from '@/redux/reducers/language.slice';
import LangUtils from '@/utils/Lang.utils';

export default function useTranslation() {
  const language = useLanguageSelector();

  const [messages, setMessages] = useState(LangUtils.getTranslationFile(language));

  useEffect(() => {
    setMessages(LangUtils.getTranslationFile(language));
  }, [language]);

  return { language, messages };
}
