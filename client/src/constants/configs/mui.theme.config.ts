import { createTheme } from '@mui/material';

import { Theme } from '@/services/storages/Theme.storage.ts';

const muiThemeConfig = (theme: Theme) => createTheme({
  palette: {
    mode: theme,
  },
});

export default muiThemeConfig;
