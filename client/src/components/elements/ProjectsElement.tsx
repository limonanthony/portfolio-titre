import { faker } from '@faker-js/faker';
import { Paper } from '@mui/material';
import { FormattedMessage } from 'react-intl';

import Element from '@/components/ui/layout/Element';
import ProjectComponent from '@/components/ui/misc/Project.tsx';
import ElementTitle from '@/components/ui/texts/Element.title';
import ElementId from '@/enums/Element.id.enum';

export default function ProjectsElement() {
  return (
    <Element className="gap-12" id={ElementId.Projects}>
      <ElementTitle>
        <FormattedMessage defaultMessage="Projects" />
      </ElementTitle>
      <Paper className="flex w-full flex-1 flex-col">
        <ProjectComponent
          description={faker.lorem.lines(10)}
          images={[]}
          skills={[
            {
              description:
                "Rédiger un Cahier Des Charges (CDC) en partant d'une expression de besoins, afin de cadrer fonctionnellement un projet de solution web dans le respect des réglementations en vigueur et notamment le RGPD",
              label: 'Rédiger un Cahier Des Charges',
            },
            {
              description:
                'Rédiger des spécifications techniques en analysant un CDC, afin de cadrer techniquement un projet de développement de solution web',
              label: 'Rédaction de Spécifications Techniques',
            },
            {
              description:
                'Déployer un environnement de travail en mettant en place les outils de versionnage, de partage et de collaboration/communication nécessaires, afin de cadrer opérationnellement un projet de développement de solution web',
              label: "Déploiement d'un environnement de travail",
            },
            {
              description:
                'Rédiger une présentation pour présenter les choix techniques, les maquettes, et le schéma de la solution web en argumentant les choix faits afin de permettre au client ou au décideur de valider la proposition de solution',
              label: "Présentation d'une Solution Web",
            },
            {
              description:
                'Développer le prototype de la solution web afin de présenter l’architecture technique au client',
              label: 'Développer un prototype',
            },
            {
              description:
                'Rédiger le code de la solution en transcrivant les fonctionnalités du CDC, en respectant les normes d’accessibilité, d’ergonomie, de référencement, et la réglementation en vigueur afin de développer la solution web',
              label: 'Développement conforme aux normes du CDC',
            },
          ]}
          title="Projet de fin d'étude"
        />
      </Paper>
    </Element>
  );
}
