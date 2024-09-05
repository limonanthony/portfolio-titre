import { AppBar, Box, Toolbar } from '@mui/material';
import { FormattedMessage } from 'react-intl';

import ProfileElement from '@/components/elements/ProfileElement.tsx';
import LanguageButton from '@/components/ui/buttons/LanguageButton.tsx';
import ThemeButton from '@/components/ui/buttons/ThemeButton.tsx';
import NavBarLink from '@/components/ui/links/NavBarLink.tsx';
import ElementId from '@/enums/Element.id.enum';
import useScrollTo from '@/hooks/use.scroll.to';

export default function NavBar() {
  const { scrollToId } = useScrollTo();
  return (
    <AppBar id="navbar" position="fixed" sx={(theme) => ({ backgroundColor: theme.palette.background.paper })}>
      <Toolbar className="z-10 flex h-20 min-h-20 w-full items-center justify-between gap-4 px-16 py-4">
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
      </Toolbar>
    </AppBar>
  );
}
