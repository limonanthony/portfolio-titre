import { Box } from '@mui/material';
import { Outlet } from 'react-router-dom';

import ScrollToTopButton from '@/components/ui/buttons/Scroll.to.top.button';
import ParticleMisc from '@/components/ui/misc/ParticleMisc';
import NavBar from '@/components/ui/navigation/NavBar';
import ElementId from '@/enums/Element.id.enum';
import { ChildrenProp } from '@/types/prop.types';

export interface AppLayoutProps extends Partial<ChildrenProp> {}

export default function AppLayout({ children }: AppLayoutProps) {
  return (
    <Box className="flex max-h-screen flex-1 flex-col overflow-hidden">
      <NavBar />
      <Box className="mt-20 flex flex-1 flex-col overflow-y-scroll" id={ElementId.Body}>
        {children ?? <Outlet />}
        <ParticleMisc />
        <ScrollToTopButton />
      </Box>
    </Box>
  );
}
