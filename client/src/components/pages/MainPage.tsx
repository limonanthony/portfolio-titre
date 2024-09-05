import { Box, Divider } from '@mui/material';

import AboutElement from '@/components/elements/AboutElement';
import ContactElement from '@/components/elements/Contact.element';
import HomeElement from '@/components/elements/HomeElement';
import ProjectsElement from '@/components/elements/ProjectsElement';

export default function MainPage() {
  return (
    <Box className="size-full">
      <HomeElement />
      <Divider />
      <AboutElement />
      <Divider />
      <ProjectsElement />
      <Divider />
      <ContactElement />
    </Box>
  );
}
