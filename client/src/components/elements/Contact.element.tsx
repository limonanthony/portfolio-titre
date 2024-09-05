import { Box } from '@mui/material';
import { FormattedMessage } from 'react-intl';

import ReviewForm from '@/components/forms/Review.form.tsx';
import Element from '@/components/ui/layout/Element';
import ElementTitle from '@/components/ui/texts/Element.title';
import ElementId from '@/enums/Element.id.enum';

export default function ContactElement() {
  return (
    <Element className="flex flex-col gap-12" id={ElementId.Contact}>
      <ElementTitle>
        <FormattedMessage defaultMessage="Contact" />
      </ElementTitle>
      <Box className="flex flex-1 flex-col items-center justify-center gap-4">
        <ReviewForm className="w-96 max-w-full" onSubmit={console.debug} />
      </Box>
    </Element>
  );
}
