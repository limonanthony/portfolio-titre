import NiceModal from '@ebay/nice-modal-react';
import { CssBaseline, ThemeProvider } from '@mui/material';
import { SnackbarProvider } from 'notistack';
import { IntlProvider } from 'react-intl';
import { RouterProvider } from 'react-router-dom';

import useMaterialTheme from '@/hooks/use.material.theme';
import useTranslation from '@/hooks/use.translation';
import router from '@/router';

export default function App() {
  const theme = useMaterialTheme();
  const { language, messages } = useTranslation();

  return (
    <IntlProvider locale={language} messages={messages}>
      <ThemeProvider theme={theme}>
        <SnackbarProvider autoHideDuration={2000} maxSnack={3}>
          <CssBaseline />
          <NiceModal.Provider>
            <RouterProvider router={router} />
          </NiceModal.Provider>
        </SnackbarProvider>
      </ThemeProvider>
    </IntlProvider>
  );
}
