import { Box } from '@mui/material';

import ElementId from '@/enums/Element.id.enum';
import { ChildrenProp, ClassNameProp } from '@/types/prop.types';
import ClassUtils from '@/utils/Class.utils';

export interface ElementProps extends Partial<ClassNameProp>, ChildrenProp {
  id: ElementId;
}

export default function Element({ children, className, id }: ElementProps) {
  return (
    <Box
      className={ClassUtils.concat(
        'flex min-h-[calc(100vh-80px)] flex-1 snap-start flex-col items-center py-20',
        className,
      )}
      id={id}
    >
      {children}
    </Box>
  );
}
