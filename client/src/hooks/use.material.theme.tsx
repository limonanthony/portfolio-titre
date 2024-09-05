import { useMemo } from 'react';

import muiThemeConfig from '@/constants/configs/mui.theme.config';
import { useThemeSelector } from '@/redux/reducers/theme.slice';

export default function useMaterialTheme() {
  const theme = useThemeSelector();

  return useMemo(() => muiThemeConfig(theme), [theme]);
}
