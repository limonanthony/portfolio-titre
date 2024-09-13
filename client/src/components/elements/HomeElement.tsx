import { Box, Button, Typography } from '@mui/material';
import { FormattedMessage } from 'react-intl';

import MouseImage from '@/components/ui/images/Mouse.image';
import Element from '@/components/ui/layout/Element';
import ElementId from '@/enums/Element.id.enum';
import useScrollTo from '@/hooks/use.scroll.to';

export default function HomeElement() {
  const { scrollToId } = useScrollTo();
  return (
    <Element className="items-center justify-center" id={ElementId.Home}>
      <Box className="xxl:px-[20%] my-auto flex w-fit flex-col justify-center gap-12 self-center px-12">
        <Typography
          align="center"
          className="w-fit self-center uppercase !tracking-widest"
          color="primary"
          fontWeight="bolder"
          variant="h3"
        >
          <FormattedMessage defaultMessage="Hey, I'm Anthony Limon" />
        </Typography>
        <Typography align="center" className="max-w-full self-center text-wrap" variant="h5">
          <FormattedMessage
            defaultMessage="A Full-Stack Developer building and managing Websites and Web Applications that leads to the success of the overall product"
            description="small description about author"
          />
        </Typography>
        <Button className="h-14 w-56 self-center" onClick={scrollToId(ElementId.Projects)} variant="contained">
          <Typography fontSize={20} fontWeight="bolder" variant="button">
            <FormattedMessage defaultMessage="Projects" description="A programmation project" />
          </Typography>
        </Button>
      </Box>
      <MouseImage className="mt-8 md:mt-auto" />
    </Element>
  );
}
