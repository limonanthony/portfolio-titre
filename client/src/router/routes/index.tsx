import MainPage from '@/components/pages/MainPage';
import AppLayout from '@/components/ui/layout/AppLayout';

export default [
  {
    children: [
      {
        element: <MainPage />,
        path: '',
      },
    ],
    element: <AppLayout />,
  },
];
