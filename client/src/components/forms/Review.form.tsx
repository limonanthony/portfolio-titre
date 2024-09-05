import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';

import ReviewFormStructure, { ReviewFormSchema } from '@/classes/schemas/form/Review.form.structure.ts';
import LoadingButton from '@/components/ui/buttons/Loading.button.tsx';
import ControlledRatingInput from '@/components/ui/inputs/Controlled.rating.input.tsx';
import ControlledTextInput from '@/components/ui/inputs/Controlled.text.input.tsx';
import { FormProps } from '@/types/prop.types.ts';
import ClassUtils from '@/utils/Class.utils.ts';

export default function ReviewForm(props: FormProps<ReviewFormSchema>) {
  const { className, isLoading, onSubmit } = props;
  const formMethods = useForm({
    defaultValues: ReviewFormStructure.defaultValues,
    resolver: zodResolver(ReviewFormStructure.schema),
  });
  const { control, formState, handleSubmit } = formMethods;
  const { isDirty } = formState;

  function submit() {
    handleSubmit(onSubmit)();
  }

  return (
    <form
      className={ClassUtils.concat('flex flex-col gap-8', className)}
      onSubmit={(e) => {
        e.preventDefault();
        submit();
      }}
    >
      <ControlledTextInput className="w-full" control={control} label="Email" name="email" variant="outlined" />
      <ControlledTextInput
        control={control}
        label="Content"
        multiline
        name="content"
        required
        rows={10}
        variant="outlined"
      />
      <ControlledRatingInput className="mx-auto" control={control} name="rating" />
      <LoadingButton isDisabled={!isDirty} isLoading={isLoading} size="large" type="submit">
        Submit
      </LoadingButton>
    </form>
  );
}
