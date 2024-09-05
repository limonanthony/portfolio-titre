import { Moon, Sun } from 'lucide-react';

import NavBarLink from '@/components/ui/links/NavBarLink';
import { useAppDispatch } from '@/hooks/redux.hooks';
import { themeActions, useThemeSelector } from '@/redux/reducers/theme.slice';
import { ThemeChoices } from '@/services/storages/Theme.storage';
import { ClassNameProp } from '@/types/prop.types';
import ClassUtils from '@/utils/Class.utils';

export interface ThemeButtonProps extends Partial<ClassNameProp> {}

export default function ThemeButton({ className }: ThemeButtonProps) {
  const dispatch = useAppDispatch();
  const theme = useThemeSelector();

  const toggleTheme = () => {
    dispatch(themeActions.toggle());
  };

  return (
    <NavBarLink
      className={ClassUtils.concat('relative flex h-8 min-w-8 items-center justify-center', className)}
      onClick={toggleTheme}
    >
      <Sun
        className={ClassUtils.concat('absolute size-6 opacity-0', {
          'opacity-100': theme === ThemeChoices.light,
        })}
      />
      <Moon
        className={ClassUtils.concat('absolute size-6 opacity-0', {
          'opacity-100': theme === ThemeChoices.dark,
        })}
      />
    </NavBarLink>
  );
}
