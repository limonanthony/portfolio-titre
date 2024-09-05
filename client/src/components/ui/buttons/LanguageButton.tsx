import { Box } from '@mui/material';

import NavBarLink from '@/components/ui/links/NavBarLink';
import { useAppDispatch } from '@/hooks/redux.hooks';
import { languageActions, useLanguageSelector } from '@/redux/reducers/language.slice';
import { LanguageChoices } from '@/services/storages/Language.storage';
import { ClassNameProp } from '@/types/prop.types';
import ClassUtils from '@/utils/Class.utils';

export interface LanguageButtonProps extends Partial<ClassNameProp> {}

export default function LanguageButton(props: LanguageButtonProps) {
  const dispatch = useAppDispatch();
  const language = useLanguageSelector();

  function toggleLanguage() {
    dispatch(languageActions.toggle());
  }

  return (
    <NavBarLink
      className={ClassUtils.concat('relative h-8 w-fit min-w-8 text-center', props.className)}
      onClick={toggleLanguage}
    >
      <Box
        className={ClassUtils.concat(
          'absolute left-1/2 top-1/2 flex -translate-x-1/2 -translate-y-1/2 items-center justify-center opacity-0 transition-opacity duration-300',
          {
            'opacity-100': language === LanguageChoices.en,
          },
        )}
      >
        EN
      </Box>
      <Box
        className={ClassUtils.concat(
          'absolute left-1/2 top-1/2 flex -translate-x-1/2 -translate-y-1/2 items-center justify-center opacity-0 transition-opacity duration-300',
          {
            'opacity-100': language === LanguageChoices.fr,
          },
        )}
      >
        FR
      </Box>
    </NavBarLink>
  );
}
