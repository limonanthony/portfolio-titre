import {
  Box, Divider, Typography, TypographyProps,
} from '@mui/material';

import { Optional } from '@/types';
import { ChildrenProp, ClassNameProp } from '@/types/prop.types';
import ClassUtils from '@/utils/Class.utils';

export interface ElementTitleProps extends Partial<ClassNameProp>, ChildrenProp {
  showUnderline?: Optional<boolean>;
  sx?: TypographyProps['sx'];
}

export default function ElementTitle({
  children, className, showUnderline = true, sx,
}: ElementTitleProps) {
  return (
    <Box className={ClassUtils.concat('flex size-fit flex-col gap-4', className)}>
      <Typography className="uppercase !tracking-widest" fontWeight="bolder" sx={sx} variant="h3">
        {children}
      </Typography>
      {showUnderline && (
        <Divider
          className="w-1/4 self-center rounded !border-b-8"
          sx={(theme) => ({
            borderColor: theme.palette.primary.main,
          })}
        />
      )}
    </Box>
  );
}
