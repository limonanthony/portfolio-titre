import { Box } from '@mui/material';
import { FormattedMessage } from 'react-intl';
import Carousel from 'react-material-ui-carousel';

import CompanyProject from '@/components/projects/Company.project.tsx';
import GroupProject from '@/components/projects/Group.project.tsx';
import PortfolioProject from '@/components/projects/Porftolio.project.tsx';
import Element from '@/components/ui/layout/Element';
import ElementTitle from '@/components/ui/texts/Element.title';
import ElementId from '@/enums/Element.id.enum';

export default function ProjectsElement() {
  return (
    <Element className="gap-12" id={ElementId.Projects}>
      <ElementTitle>
        <FormattedMessage defaultMessage="Projects" />
      </ElementTitle>
      <Box className="flex size-full flex-1">
        <Carousel animation="slide" autoPlay={false} className="flex-1" navButtonsAlwaysVisible>
          <GroupProject />
          <PortfolioProject />
          <CompanyProject />
        </Carousel>
      </Box>
    </Element>
  );
}
