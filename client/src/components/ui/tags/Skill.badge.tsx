import { Chip } from '@mui/material';
import { ReactElement } from 'react';

import { ChildrenProp, ClassNameProp } from '@/types/prop.types';
import ClassUtils from '@/utils/Class.utils';

export interface SkillBadgeProps extends Partial<ClassNameProp>, ChildrenProp {
  icon?: ReactElement;
}

export default function SkillBadge({ children, className, icon }: SkillBadgeProps) {
  return (
    <Chip
      className={ClassUtils.concat('select-none transition-colors duration-1000 hover:bg-blue-400', className)}
      color="primary"
      icon={icon}
      label={children}
      sx={{
        cursor: 'pointer',
      }}
    />
  );
}
