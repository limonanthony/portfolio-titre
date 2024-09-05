import { PayloadAction, createSlice } from '@reduxjs/toolkit';

import { useAppSelector } from '@/hooks/redux.hooks.tsx';
import LanguageStorage, { Language, LanguageChoices } from '@/services/storages/Language.storage';

interface LanguageState {
  value: Language;
}

const initialState: LanguageState = {
  value: LanguageStorage.get(LanguageChoices.en),
};

const languageSlice = createSlice({
  initialState,
  name: 'language',
  reducers: {
    set(state: LanguageState, action: PayloadAction<Language>) {
      state.value = action.payload;
      LanguageStorage.set(action.payload);
    },
    toggle(state: LanguageState) {
      const value = state.value === LanguageChoices.en ? LanguageChoices.fr : LanguageChoices.en;
      state.value = value;
      LanguageStorage.set(value);
    },
  },
});

export const languageActions = languageSlice.actions;
export const useLanguageSelector = () => useAppSelector((state) => state.language.value);

export default languageSlice.reducer;
