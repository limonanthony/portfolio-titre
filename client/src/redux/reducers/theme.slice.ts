import { PayloadAction, createSlice } from '@reduxjs/toolkit';

import { useAppSelector } from '@/hooks/redux.hooks.tsx';
import ThemeStorage, { Theme, ThemeChoices } from '@/services/storages/Theme.storage.ts';
import ThemeUtils from '@/utils/Theme.utils';

interface ThemeState {
  value: Theme;
}

const initialState: ThemeState = {
  value: ThemeStorage.get(ThemeChoices.light),
};

ThemeUtils.setTailwindTheme(initialState.value);

const themeSlice = createSlice({
  initialState,
  name: 'theme',
  reducers: {
    set: (state: ThemeState, action: PayloadAction<Theme>) => {
      state.value = action.payload;
      ThemeUtils.setTailwindTheme(action.payload);
      ThemeStorage.set(action.payload);
    },
    toggle(state: ThemeState) {
      if (state.value === ThemeChoices.light) {
        ThemeUtils.setDarkThemeTailwind();
        state.value = ThemeChoices.dark;
        ThemeStorage.set(ThemeChoices.dark);
      } else {
        ThemeUtils.setLightThemeTailwind();
        state.value = ThemeChoices.light;
        ThemeStorage.set(ThemeChoices.light);
      }
    },
  },
});

export const themeActions = themeSlice.actions;
export const useThemeSelector = () => useAppSelector((state) => state.theme.value);

export default themeSlice.reducer;
