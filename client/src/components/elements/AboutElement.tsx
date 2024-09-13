import { Box, Button, Typography } from '@mui/material';
import { FileDownIcon } from 'lucide-react';
import { useRef } from 'react';
import { FormattedMessage } from 'react-intl';

import CIcon from '@/assets/icons/c-icon.svg?react';
import CSharpIcon from '@/assets/icons/csharp-icon.svg?react';
import DockerIcon from '@/assets/icons/docker-icon.svg?react';
import GraphqlIcon from '@/assets/icons/graphql-icon.svg?react';
import JavaIcon from '@/assets/icons/java-icon.svg?react';
import JsIcon from '@/assets/icons/js-icon.svg?react';
import KotlinIcon from '@/assets/icons/kotlin-icon.svg?react';
import NestjsIcon from '@/assets/icons/nestjs-icon.svg?react';
import PhpIcon from '@/assets/icons/php-icon.svg?react';
import PostgresIcon from '@/assets/icons/postgres-icon.svg?react';
import ReactIcon from '@/assets/icons/react-icon.svg?react';
import SassIcon from '@/assets/icons/sass-icon.svg?react';
import TsIcon from '@/assets/icons/ts-icon.svg?react';
import CvPdf from '@/assets/pdf/cv.pdf';
import DescriptionParagraph from '@/components/elements/paragraphs/Description.paragraph';
import Element from '@/components/ui/layout/Element';
import SkillBadge from '@/components/ui/tags/Skill.badge';
import ElementTitle from '@/components/ui/texts/Element.title';
import ElementId from '@/enums/Element.id.enum';

const iconClassNames = 'size-8 py-1 rounded-full fill-white dark:fill-black';

const languages = [
  {
    icon: <TsIcon className={iconClassNames} />,
    label: 'TypeScript',
    url: null,
  },
  {
    icon: <ReactIcon className={iconClassNames} />,
    label: 'React',
    url: null,
  },
  {
    icon: <NestjsIcon className={iconClassNames} />,
    label: 'NestJS',
    url: null,
  },
  {
    icon: <KotlinIcon className={iconClassNames} />,
    label: 'Kotlin',
    url: null,
  },
  {
    icon: <PhpIcon className={iconClassNames} />,
    label: 'PHP',
    url: null,
  },
  {
    icon: <JsIcon className={iconClassNames} />,
    label: 'JavaScript',
    url: null,
  },
  {
    icon: <GraphqlIcon className={iconClassNames} />,
    label: 'GraphQL',
    url: null,
  },
  {
    icon: <PostgresIcon className={iconClassNames} />,
    label: 'Postgres',
    url: null,
  },
  {
    icon: <DockerIcon className={iconClassNames} />,
    label: 'Docker',
    url: null,
  },
  {
    icon: <SassIcon className={iconClassNames} />,
    label: 'SASS',
    url: null,
  },
  {
    icon: <CIcon className={iconClassNames} />,
    label: 'C',
    url: null,
  },
  {
    icon: <JavaIcon className={iconClassNames} />,
    label: 'Java',
    url: null,
  },
  {
    icon: <CSharpIcon className={iconClassNames} />,
    label: 'C#',
    url: null,
  },
];

export default function AboutElement() {
  const cvRef = useRef<HTMLAnchorElement>(null);

  return (
    <Element className="items-center gap-12 px-12" id={ElementId.About}>
      <ElementTitle>
        <FormattedMessage defaultMessage="About Me" />
      </ElementTitle>
      <Typography className="max-w-screen-md text-center" variant="body1">
        <FormattedMessage
          defaultMessage=" Here you will find more information about me, what I do, and my current skills mostly in terms of programming and technology "
          description="about me page presentation"
        />
      </Typography>
      <Box
        className="lg:px16 flex w-full flex-1 flex-col gap-4 transition-all duration-300 md:gap-16 lg:flex-row xl:px-32"
      >
        <Box className="flex flex-1 flex-col gap-6">
          <Typography fontWeight="bold" variant="h5">
            <FormattedMessage defaultMessage="Get to know me" />
          </Typography>
          <DescriptionParagraph>
            <FormattedMessage
              defaultMessage="I enjoy creating new mobile applications/websites and exploring new technologies. I am an enthusiastic
            web/mobile developer, which is why I recently made the decision to transition my career towards web
            development."
            />
          </DescriptionParagraph>
          <DescriptionParagraph>
            <FormattedMessage
              defaultMessage="As a result, I am currently seeking an apprenticeship opportunity that will allow me to gain experience in the field of web development."
            />
          </DescriptionParagraph>
          <DescriptionParagraph>
            <FormattedMessage
              defaultMessage="I am primarily looking for a company that works with React.js and Node.js, as these are technologies that greatly interest me."
            />
          </DescriptionParagraph>
        </Box>
        <Box className="flex flex-1 flex-col gap-6">
          <Typography fontWeight="bold" variant="h5">
            <FormattedMessage defaultMessage="My Skills" />
          </Typography>
          <Box className="flex flex-wrap gap-6">
            {languages.map((language) => (
              <SkillBadge icon={language.icon} key={language.label}>
                {language.label}
              </SkillBadge>
            ))}
          </Box>
        </Box>
      </Box>
      <Box className="flex w-full justify-center py-8">
        <Button
          className="flex gap-2 !px-4 !py-2 !text-2xl"
          onClick={() => cvRef.current?.click()}
          variant="contained"
        >
          <FileDownIcon />
          <span>CV</span>
        </Button>
        {/* eslint-disable-next-line jsx-a11y/anchor-has-content,jsx-a11y/control-has-associated-label */}
        <a
          className="hidden"
          download="CV-Anthony-Limon.pdf"
          href={CvPdf}
          ref={cvRef}
          rel="noreferrer"
          target="_blank"
        />
      </Box>
    </Element>
  );
}
