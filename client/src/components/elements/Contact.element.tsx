import { Box } from '@mui/material';
import { useMutation } from '@tanstack/react-query';
import { useSnackbar } from 'notistack';
import { FormattedMessage } from 'react-intl';

import ReviewRequests from '@/classes/network/requests/Review.requests.ts';
import ReviewForm from '@/components/forms/Review.form.tsx';
import Element from '@/components/ui/layout/Element';
import ElementTitle from '@/components/ui/texts/Element.title';
import ElementId from '@/enums/Element.id.enum';

export default function ContactElement() {
  const { enqueueSnackbar } = useSnackbar();

  const { isPending, mutate } = useMutation({
    mutationFn: ReviewRequests.create,
    onError: (error) => enqueueSnackbar(error.message ?? 'Une erreur est survenue', { variant: 'error' }),
    onSuccess: () => enqueueSnackbar('Review sent. Thank you for the feedback !', { variant: 'success' }),
  });

  return (
    <Element className="flex flex-col gap-12" id={ElementId.Contact}>
      <ElementTitle>
        <FormattedMessage defaultMessage="Contact" />
      </ElementTitle>
      <Box className="flex flex-1 flex-col items-center justify-center gap-4">
        <ReviewForm className="w-96 max-w-full px-8" isLoading={isPending} onSubmit={mutate} />
      </Box>
    </Element>
  );
}
