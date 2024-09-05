import { Button, ButtonProps, CircularProgress } from '@mui/material';

import { ChildrenProp, ClassNameProp } from '@/types/prop.types.ts';

export interface LoadingButtonProps extends Partial<ClassNameProp>, ChildrenProp {
  isDisabled?: boolean;
  isLoading?: boolean;
  onClick?: () => void;
  size?: ButtonProps['size'];
  type?: ButtonProps['type'];
  variant?: ButtonProps['variant'];
}

export default function LoadingButton(props: LoadingButtonProps) {
  const {
    children, className, isDisabled, isLoading, onClick, size = 'medium', type, variant = 'contained',
  } = props;

  return (
    <Button className={className} disabled={isDisabled} onClick={onClick} size={size} type={type} variant={variant}>
      {isLoading ? <CircularProgress /> : children}
    </Button>
  );
}
