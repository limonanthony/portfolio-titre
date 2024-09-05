import {
  AppBar, Box, Drawer, IconButton, List, ListItemButton, Toolbar, useMediaQuery,
} from '@mui/material';
import { MenuIcon } from 'lucide-react';
import { useState } from 'react';
import { FormattedMessage } from 'react-intl';

import ProfileElement from '@/components/elements/ProfileElement.tsx';
import LanguageButton from '@/components/ui/buttons/LanguageButton.tsx';
import ThemeButton from '@/components/ui/buttons/ThemeButton.tsx';
import NavBarLink from '@/components/ui/links/NavBarLink.tsx';
import ElementId from '@/enums/Element.id.enum';
import useScrollTo from '@/hooks/use.scroll.to';

function NavBarDesktop() {
  const { scrollToId } = useScrollTo();
  return (
    <Box className="flex flex-1 justify-between">
      <ProfileElement onClick={scrollToId(ElementId.Home)} />
      <Box className="flex gap-10">
        <Box className="flex items-center gap-6">
          <NavBarLink onClick={scrollToId(ElementId.Home)}>
            <FormattedMessage defaultMessage="Home" description="Home navigation" />
          </NavBarLink>
          <NavBarLink className="size-fit" onClick={scrollToId(ElementId.About)}>
            <FormattedMessage defaultMessage="About" description="About me navigation" />
          </NavBarLink>
          <NavBarLink onClick={scrollToId(ElementId.Projects)}>
            <FormattedMessage defaultMessage="Projects" description="Projects navigation" />
          </NavBarLink>
          <NavBarLink onClick={scrollToId(ElementId.Contact)}>
            <FormattedMessage defaultMessage="Contact" description="Contact me navigation" />
          </NavBarLink>
        </Box>
        <Box className="flex flex-1 items-center gap-2">
          <LanguageButton />
          <ThemeButton />
        </Box>
      </Box>
    </Box>
  );
}

function NavBarMobile() {
  const { scrollToId } = useScrollTo();
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  return (
    <>
      <Box className="flex flex-1 gap-6">
        <IconButton aria-label="menu" edge="start" onClick={() => setIsDrawerOpen(true)} size="large">
          <MenuIcon />
        </IconButton>
        <ProfileElement onClick={scrollToId(ElementId.Home)} />
      </Box>
      <Drawer onClose={() => setIsDrawerOpen(false)} open={isDrawerOpen}>
        <Box className="flex flex-1 flex-col justify-between gap-10">
          <List className="!py-0">
            <ListItemButton className="flex !min-w-40 !justify-center !py-4" onClick={scrollToId(ElementId.Home)}>
              <NavBarLink>
                <FormattedMessage defaultMessage="Home" description="Home navigation" />
              </NavBarLink>
            </ListItemButton>
            <ListItemButton className="flex !justify-center !py-4" onClick={scrollToId(ElementId.Home)}>
              <NavBarLink className="size-fit" onClick={scrollToId(ElementId.About)}>
                <FormattedMessage defaultMessage="About" description="About me navigation" />
              </NavBarLink>
            </ListItemButton>
            <ListItemButton className="flex !justify-center !py-4" onClick={scrollToId(ElementId.Home)}>
              <NavBarLink onClick={scrollToId(ElementId.Projects)}>
                <FormattedMessage defaultMessage="Projects" description="Projects navigation" />
              </NavBarLink>
            </ListItemButton>
            <ListItemButton className="flex !justify-center !py-4" onClick={scrollToId(ElementId.Home)}>
              <NavBarLink onClick={scrollToId(ElementId.Contact)}>
                <FormattedMessage defaultMessage="Contact" description="Contact me navigation" />
              </NavBarLink>
            </ListItemButton>
          </List>
          <List className="flex flex-col py-6">
            <ListItemButton className="flex !justify-center !py-4" onClick={scrollToId(ElementId.Home)}>
              <LanguageButton />
            </ListItemButton>
            <ListItemButton className="flex !justify-center !py-4" onClick={scrollToId(ElementId.Home)}>
              <ThemeButton />
            </ListItemButton>
          </List>
        </Box>
      </Drawer>
    </>
  );
}

export default function NavBar() {
  const matches = useMediaQuery('(max-width:790px)');
  return (
    <AppBar id="navbar" position="fixed" sx={(theme) => ({ backgroundColor: theme.palette.background.paper })}>
      <Toolbar className="z-10 flex h-20 min-h-20 w-full items-center gap-4 px-16 py-4">
        {matches ? <NavBarMobile /> : <NavBarDesktop />}
      </Toolbar>
    </AppBar>
  );
}
