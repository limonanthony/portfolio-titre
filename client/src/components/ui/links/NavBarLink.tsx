import { Link } from '@mui/material';

import { Optional } from '@/types';
import { ChildrenProp, ClassNameProp } from '@/types/prop.types';
import ClassUtils from '@/utils/Class.utils';

export interface NavBarLinkProps extends Partial<ClassNameProp>, ChildrenProp {
  href?: Optional<string>;
  onClick?: () => void;
}

export default function NavBarLink({
  children, className, href, onClick,
}: NavBarLinkProps) {
  return (
    <Link
      className={ClassUtils.concat('cursor-pointer select-none uppercase transition-colors duration-300', className)}
      color={(theme) => theme.palette.text.primary}
      fontWeight="bold"
      href={href}
      onClick={onClick}
      sx={(theme) => ({ ':hover': { color: theme.palette.primary.main } })}
      underline="none"
      variant="h6"
    >
      {children}
    </Link>
  );
}
