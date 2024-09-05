import { Box } from '@mui/material';
import { Mouse } from 'lucide-react';

import { ClassNameProp } from '@/types/prop.types';
import ClassUtils from '@/utils/Class.utils';

import '@/components/ui/images/Mouse.image.css';

export interface MouseImageProps extends Partial<ClassNameProp> {}

export default function MouseImage({ className }: MouseImageProps) {
  return (
    <Box className={ClassUtils.concat('mouse-image size-fit', className)}>
      <Mouse className="size-10" />
    </Box>
  );
}
