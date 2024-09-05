import { faker } from '@faker-js/faker';
import { Chip, Tooltip, Typography } from '@mui/material';
import { ReactNode } from 'react';
import Carousel from 'react-material-ui-carousel';

import { ChildrenProp } from '@/types/prop.types.ts';

export interface ProjectImageProps {
  alt: string;
  src: string;
}

export interface ProjectSkillProps {
  description: string;
  label: string;
}

export function ProjectTitle({ children }: ChildrenProp) {
  return <Typography variant="h4">{children}</Typography>;
}

export function ProjectDescription({ children }: ChildrenProp) {
  return <Typography variant="body1">{children}</Typography>;
}

export function ProjectImage({ alt, src }: ProjectImageProps) {
  return <img alt={alt} src={src} />;
}

export function ProjectImages({ images }: { images: ProjectImageProps[] }) {
  return (
    <Carousel animation="fade" autoPlay className="mb-4 h-full flex-1" navButtonsAlwaysInvisible>
      {images.map(({ alt, src }) => (
        <ProjectImage alt={alt} key={faker.string.uuid()} src={src} />
      ))}
    </Carousel>
  );
}

export function ProjectSkill({ description, label }: ProjectSkillProps) {
  return (
    <Tooltip title={description}>
      <Chip className="select-none" label={label} />
    </Tooltip>
  );
}

export function ProjectSkills({ skills }: { skills: ProjectSkillProps[] }) {
  return (
    <div className="flex w-full flex-col gap-4">
      <Typography color="primary" variant="h6">
        Compétences
      </Typography>
      <div className="flex flex-wrap gap-4">
        {skills.map(({ description, label }) => (
          <ProjectSkill description={description} key={faker.string.uuid()} label={label} />
        ))}
      </div>
    </div>
  );
}

export interface ProjectComponentProps {
  description: ReactNode;
  images: ProjectImageProps[];
  skills: ProjectSkillProps[];
  title: string;
}

export default function ProjectComponent(props: ProjectComponentProps) {
  const {
    description, images, skills, title,
  } = props;
  return (
    <div className="flex flex-1 px-8">
      <div className="flex flex-1 flex-col justify-between py-8">
        <ProjectTitle>{title}</ProjectTitle>
        <ProjectDescription>{description}</ProjectDescription>
        <ProjectSkills skills={skills} />
      </div>
      <div className="flex flex-1">
        <ProjectImages images={images} />
      </div>
    </div>
  );
}
