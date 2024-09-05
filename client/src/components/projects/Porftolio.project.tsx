import ProjectComponent, {
  ProjectDescriptionText,
  ProjectDescriptionTitle,
} from '@/components/ui/misc/Project.component.tsx';

export default function PortfolioProject() {
  return (
    <ProjectComponent
      description={(
        <div>
          <ProjectDescriptionTitle>Technologies utilisés</ProjectDescriptionTitle>
          <ProjectDescriptionText>
            Front-end: TypeScript, React, MaterialUI, TailwindCSS ,React Query, React Router, react-hook-form, zod
          </ProjectDescriptionText>
          <ProjectDescriptionText>
            Back-end: Kotlin, Ktor, S3, Exposed, JWT, Koin, Kotest, PostgeSQL
          </ProjectDescriptionText>
          <br />
          <ProjectDescriptionText>
            Le but de ce projet est de montrer que les compétences pour valider la certifications ont été acquises. Je
            n&apos;ai pas pu finir le projet entièrement et beaucoup de fonctionnalités n&apos;ont pas pu être développé
            dans le front-end, comme sauvegarder les projets dans le back-end, gérer les images avec S3, la création de
            compte et la connexion. Ces fonctionnalités sont fonctionnel dans le back-end mais il n&apos;y a pas de
            routes pour.
          </ProjectDescriptionText>
        </div>
      )}
      images={[]}
      skills={[
        {
          description:
            "Identifier les fonctionnalités à développer, en modélisant les divers éléments et leurs interconnexions, afin de structurer l'architecture de la solution web et de Base De Données (BDD)",
          label: 'Identification des fonctionnalités à développer',
        },
        {
          description: 'Implémenter la partie "front-end" d\'une solution web',
          label: 'Implémentation du front-end',
        },
        {
          description:
            'Implémenter la logique et la base de données assurant la persistance des données côté serveur (le "back-end")',
          label: 'Implémentation du back-end',
        },
        {
          description:
            "Implémenter des règles d'authentification, en respectant les bonnes pratiques en matière de sécurité, afin de sécuriser l'accès à une solution web",
          label: "Implémentation de l'authentification",
        },
        {
          description:
            'Implémenter un plan de tests en concevant les différents tests unitaires et d’intégration afin de vérifier que l’ensemble des fonctionnalités développées fonctionne bien séparément et à l’unisson',
          label: 'Implémentation de Test unitaires',
        },
        {
          description:
            'Déployer une application web en utilisant un serveur afin de rendre l’application accessible aux utilisateurs',
          label: 'Créer une application accessible',
        },
      ]}
      title="Portfolio (ce project)"
    />
  );
}
