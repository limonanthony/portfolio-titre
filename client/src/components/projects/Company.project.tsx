import DashboardImage from '@/assets/images/projects/company/dashboard.png';
import DashboardMobileImage from '@/assets/images/projects/company/dashboard-mobile.png';
import MaquetteDashboardImage from '@/assets/images/projects/company/maquette-dashboard.png';
import ProjectComponent, {
  ProjectDescriptionText,
  ProjectDescriptionTitle,
} from '@/components/ui/misc/Project.component.tsx';

export default function CompanyProject() {
  return (
    <ProjectComponent
      description={(
        <div>
          <ProjectDescriptionTitle>Technologies utilisés</ProjectDescriptionTitle>
          <ProjectDescriptionText>Front-end: TypeScript, React, Ant-design, Apollo Client, Zod</ProjectDescriptionText>
          <ProjectDescriptionText>
            Back-end: TypeScript, Node.js, GraphQL, Apollo Server, Sequelize, PostgreSQL, Redis
          </ProjectDescriptionText>
          <br />
          <ProjectDescriptionText>
            Ce logiciel est un ERP de gestion de maintenance pour les hôtels et restaurants.
          </ProjectDescriptionText>
          <br />
          <ProjectDescriptionText>
            Dans ce projet j&apos;ai pu rédiger une documentation technique pour les autres développeurs avec des
            readme, une documentation utilsiateur grâce à l&apos;outil LemonLearning qui permet d&apos;aider les
            utilisateurs dans l&apos;utilisation du site.
          </ProjectDescriptionText>
          <br />
          <ProjectDescriptionText>
            J&apos;ai implémenter beaucoup de maquette dans le projet, j&apos;ai aussi fait parti des réunions avec les
            clients pour l&apos;amélioration du logiciel.
          </ProjectDescriptionText>
          <br />
          <ProjectDescriptionText>
            J&apos;ai mis en place Sentry qui nous permet de recueillir des informations et des erreurs pour facilité le
            débogage.
          </ProjectDescriptionText>
          <br />
          <ProjectDescriptionText>
            J&apos;ai été dans les réunions de discussion des maquettes pour analyser comment les implémenter et les
            problèmes qui pourraient être lié
          </ProjectDescriptionText>
          <br />
          <ProjectDescriptionText>
            J&apos;ai présenter les problèmes d'utilisation de ant-design et le temps qu'il nous faisait perdre. J'ai
            aussi fait un document montrant le pour et le contre de chaque librairies et un Proof Of Concept pour
            implémenter MaterialUI et shadcn en technologies alternatifs à ant-design.
          </ProjectDescriptionText>
        </div>
      )}
      images={[
        {
          alt: 'Dashboard',
          src: DashboardImage,
        },
        {
          alt: 'Dashboard mobile',
          src: DashboardMobileImage,
        },
        {
          alt: 'Maquette dashboard',
          src: MaquetteDashboardImage,
        },
      ]}
      skills={[
        {
          description:
            'Intégrer les différents éléments de la solution web en fonction des maquettes, en respectant les dernières normes des langages utilisés (HTML, CSS, JS, …)',
          label: 'Intégration de maquettes',
        },
        {
          description:
            'Rédiger une documentation technique à destination des équipes parties prenantes en réalisant la documentation technique et fonctionnelle de la solution web, afin de garantir sa pérennité et son évolution future',
          label: "Rédaction d'une documentation technique",
        },
        {
          description:
            "Rédiger une documentation utilisateur pour apporter un support aux utilisateurs, afin de garantir l'autonomie et la satisfaction des utilisateurs de la solution web",
          label: "Rédaction d'une documentation utilsiateur",
        },
        {
          description:
            "Monitorer le lancement d'une solution web, en recueillant les retours utilisateurs, afin d'évaluer la qualité de la solution web déployée",
          label: 'Monitorer une solution web',
        },
        {
          description:
            "Identifier des améliorations qualitatives et de performance d'une solution web, en analysant les retours utilisateurs et les données d'analyse du trafic, afin d'améliorer la qualité et la disponibilité d'une solution web déployée",
          label: "Identification d'améliorations",
        },
        {
          description:
            'Analyser la qualité de l’ergonomie et la qualité de l’accessibilité de la solution (normes, design, ergonomie, navigation, référencement, bonnes pratiques, etc.) pour identifier les axes d’amélioration',
          label: "Analyse de la qualité d'une solution",
        },
        {
          description:
            "Rédiger un document argumentatif en listant des propositions d'améliorations afin de faire valider des préconisations de développements correctifs d'une solution web",
          label: "Rédaction d'un document d'amélioration technique",
        },
      ]}
      title="Inara (alternance)"
    />
  );
}
