import Particles, { initParticlesEngine } from '@tsparticles/react';
import { useEffect, useState } from 'react';
import { loadFull } from 'tsparticles';

import { useThemeSelector } from '@/redux/reducers/theme.slice.ts';

export default function ParticleMisc() {
  const theme = useThemeSelector();
  const [isInitialized, setIsInitialized] = useState(false);

  useEffect(() => {
    initParticlesEngine(async (engine) => {
      await loadFull(engine);
    }).then(() => setIsInitialized(true));
  }, []);

  if (!isInitialized) return null;

  return (
    <Particles
      className="relative -z-10"
      options={{
        background: {
          color: theme === 'dark' ? '#121212' : 'fff',
          image: '',
          position: '50% 50%',
          repeat: 'no-repeat',
          size: 'cover',
        },
        interactivity: {
          events: {
            onHover: {
              enable: true,
              mode: 'repulse',
              parallax: {
                enable: false,
                force: 1,
                smooth: 100,
              },
            },
          },
          modes: {
            bubble: {
              distance: 40,
              duration: 1,
              opacity: 8,
              size: 6,
            },
            connect: {
              distance: 200,
              links: {
                opacity: 1.0,
              },
              radius: 120,
            },
            grab: {
              distance: 100,
              links: {
                opacity: 1,
              },
            },
            push: {
              quantity: 4,
            },
            remove: {
              quantity: 2,
            },
            repulse: {
              distance: 50,
              duration: 1,
            },
            slow: {
              active: false,
              factor: 1,
              radius: 0,
            },
          },
        },
        key: 'polygonMask',
        name: 'Polygon Mask',
        particles: {
          color: {
            value: theme === 'dark' ? '#2f2f2f' : '#d7d7d7',
          },
          links: {
            blink: false,
            color: theme === 'dark' ? '#2f2f2f' : '#d7d7d7',
            consent: false,
            distance: 90,
            enable: true,
            opacity: 0.4,
            width: 1,
          },
          move: {
            enable: true,
            outModes: 'bounce',
            speed: 0.2,
          },
          number: {
            value: window.innerWidth / 5,
          },
          opacity: {
            animation: {
              enable: true,
              speed: 1,
              sync: false,
            },
            value: {
              max: 1.0,
              min: 0.5,
            },
          },
          shape: {
            type: 'circle',
          },
          size: {
            value: 1,
          },
        },
        polygon: {
          draw: {
            enable: true,
            stroke: {
              color: theme === 'dark' ? '#ffffff' : '#121212',
              opacity: 0.2,
              width: 1,
            },
          },
          enable: true,
          inline: {
            arrangement: 'equidistant',
          },
          move: {
            radius: 10,
          },
          position: {
            x: 50,
            y: 50,
          },
          scale: 0.5,
          type: 'inline',
        },
      }}
    />
  );
}
