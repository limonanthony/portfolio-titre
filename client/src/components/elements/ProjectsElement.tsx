import { faker } from '@faker-js/faker';
import { Paper } from '@mui/material';
import { FormattedMessage } from 'react-intl';
import Carousel from 'react-material-ui-carousel';

import ProjectCard, { Project } from '@/components/ui/cards/Project.card.tsx';
import Element from '@/components/ui/layout/Element';
import ElementTitle from '@/components/ui/texts/Element.title';
import ElementId from '@/enums/Element.id.enum';

const projects: Project[] = Array.from({ length: 10 }).map(() => ({
  description: faker.person.bio(),
  images: Array.from({ length: 5 }).map(() => faker.image.image()),
  skills: Array.from({ length: 5 }).map(() => faker.animal.dog()),
  title: faker.person.fullName(),
  url: faker.internet.url(),
}));

export default function ProjectsElement() {
  return (
    <Element className="gap-12" id={ElementId.Projects}>
      <ElementTitle>
        <FormattedMessage defaultMessage="Projects" />
      </ElementTitle>
      <Paper className="flex w-full flex-1 flex-col">
        <Carousel animation="slide" autoPlay={false} className="h-full flex-1" fullHeightHover sx={{ height: '100%' }}>
          {projects.map((project) => (
            <ProjectCard className="size-full" key={(Math.random() * 1000000).toString()} project={project} />
          ))}
        </Carousel>
      </Paper>
    </Element>
  );
}
