import * as path from 'node:path';

import react from '@vitejs/plugin-react';
import { defineConfig } from 'vite';
import vitePluginSvgr from 'vite-plugin-svgr';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vitePluginSvgr(),
    react({
      babel: {
        babelrc: false,
        configFile: false,
        plugins: [['formatjs', { ast: true, idInterpolationPattern: '[sha512:contenthash:base64:6]' }]],
      },
    }),
  ],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },
});
