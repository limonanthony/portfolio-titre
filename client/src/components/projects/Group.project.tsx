import ClientCreationModalImage from '@/assets/images/projects/group/client-creation-modal.png';
import ClientPageImage from '@/assets/images/projects/group/client-page.png';
import LoginPageImage from '@/assets/images/projects/group/login-page.png';
import MaquetteLoginImage from '@/assets/images/projects/group/maquette-login.png';
import ProjectComponent, {
  ProjectDescriptionText,
  ProjectDescriptionTitle,
} from '@/components/ui/misc/Project.component.tsx';

export default function GroupProject() {
  return (
    <ProjectComponent
      description={(
        <div>
          <br />
          <ProjectDescriptionTitle>Technologies utilisés</ProjectDescriptionTitle>
          <br />
          <ProjectDescriptionText>
            Front-end: TypeScript, React, MaterialUI, TailwindCSS, Zod, React Query
          </ProjectDescriptionText>
          <ProjectDescriptionText>Back-end: C#, .NET, EFCore, MySQL</ProjectDescriptionText>
          <br />
          <ProjectDescriptionText>
            Ce projet est un projet de groupe, c&apos;est un ERP pour les travailleurs indépendants dans le secteur
            technologique.
          </ProjectDescriptionText>
          <ProjectDescriptionText>
            Nous avons rédiger un cahier des charges et les spécifications techniques en partant des besoins des
            travailleurs indépendants dans le milieu technologique.
          </ProjectDescriptionText>
          <ProjectDescriptionText>
            Nous avons fait une présentation du projet, de nos choix techniques et des maquettes ce qui nous a permis
            d&apos;avoir l&apos;accord de notre pédagogue pour mettre en place le projet.
          </ProjectDescriptionText>
          <ProjectDescriptionText>
            Nous avons eu l&apos;occasion de faire un prototype du logiciel et nous avons respecté le cahier des charges
            lors du développement des fonctionnalités
          </ProjectDescriptionText>
        </div>
      )}
      images={[
        {
          alt: 'Maquette Login',
          src: MaquetteLoginImage,
        },
        {
          alt: 'Login page',
          src: LoginPageImage,
        },
        {
          alt: 'Client page',
          src: ClientPageImage,
        },
        {
          alt: 'Client creation modal',
          src: ClientCreationModalImage,
        },
      ]}
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
        {
          description:
            'Réaliser une maquette afin de permettre au client de valider la structure de la solution web en respectant les bonnes pratiques en termes d’ergonomie et d’accessibilité',
          label: 'Réalisation de maquettes',
        },
      ]}
      title="Projet de fin d'étude"
    />
  );
}
