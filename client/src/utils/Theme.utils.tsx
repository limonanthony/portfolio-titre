import { Theme, ThemeChoices } from '@/services/storages/Theme.storage';

export default class ThemeUtils {
  public static setDarkThemeTailwind() {
    document.querySelector('html')?.classList.add('dark');
  }

  public static setLightThemeTailwind() {
    document.querySelector('html')?.classList.remove('dark');
  }

  public static setTailwindTheme(theme: Theme) {
    if (theme === ThemeChoices.dark) ThemeUtils.setDarkThemeTailwind();
    else ThemeUtils.setLightThemeTailwind();
  }
}
