import { faker } from '@faker-js/faker';
import { Box, Chip, Typography } from '@mui/material';
import Carousel from 'react-material-ui-carousel';

import { ClassNameProp } from '@/types/prop.types.ts';

export interface Project {
  description: string;
  images: string[];
  skills: string[];
  title: string;
  url: string;
}

export interface ProjectCardProps extends Partial<ClassNameProp> {
  project: Project;
}

export default function ProjectCard(props: ProjectCardProps) {
  const { project } = props;

  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const buildImageUrl = (imageName: string) => `${import.meta.env.VITE_PUBLIC_URL}/images/${imageName}`;

  return (
    <Box className="flex h-[60vh] flex-1">
      <Box className="flex max-h-full flex-1 flex-col gap-4 px-4">
        <Typography align="center" className="py-4" variant="h4">
          {project.title}
        </Typography>
        <Typography variant="body1">{project.description}</Typography>
        <Box className="mt-auto flex flex-wrap gap-2 pb-4">
          {project.skills.map((skill) => (
            <Chip className="size-fit" key={faker.string.uuid()} label={skill} />
          ))}
        </Box>
      </Box>
      <Carousel animation="fade" autoPlay className="mb-4 h-full flex-1" navButtonsAlwaysInvisible>
        {project.images.map((image) => (
          <img
            alt="oula"
            className="size-full h-[50vh] select-none object-scale-down"
            key={Math.random() * 1000000}
            src={image}
          />
        ))}
      </Carousel>
    </Box>
  );
}
