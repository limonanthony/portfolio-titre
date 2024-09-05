import { ReactNode } from 'react';

import { Optional } from '@/types/index';

export interface ClassNameProp {
  className: Optional<string>;
}

export interface ChildrenProp {
  children: ReactNode;
}

export interface OnClickProps<T extends []> {
  onClick?: (...args: T) => void;
}

export interface FormProps<FormData extends Record<string, unknown>> extends Partial<ClassNameProp> {
  isLoading?: boolean;
  onSubmit: (data: FormData) => void;
}
