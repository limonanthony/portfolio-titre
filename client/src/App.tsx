import { CssBaseline, ThemeProvider } from '@mui/material';
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
        <CssBaseline />
        <RouterProvider router={router} />
      </ThemeProvider>
    </IntlProvider>
  );
}
