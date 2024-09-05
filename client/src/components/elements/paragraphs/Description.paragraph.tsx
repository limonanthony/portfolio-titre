import { Typography } from '@mui/material';

import { ChildrenProp } from '@/types/prop.types';

export interface DescriptionParagraphProps extends ChildrenProp {}

export default function DescriptionParagraph({ children }: DescriptionParagraphProps) {
  return (
    <Typography fontWeight="medium" variant="body1">
      {children}
    </Typography>
  );
}
