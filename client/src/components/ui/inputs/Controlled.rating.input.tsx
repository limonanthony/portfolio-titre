import { Rating } from '@mui/material';
import { Control, Controller, Path } from 'react-hook-form';

import { ClassNameProp } from '@/types/prop.types.ts';

export interface ControlledRatingInputProps<FormValues extends Record<string, any>> extends Partial<ClassNameProp> {
  control: Control<FormValues>;
  name: Path<FormValues>;
}

export default function ControlledRatingInput<FormValues extends Record<string, any>>(
  props: ControlledRatingInputProps<FormValues>,
) {
  const { className, control, name } = props;

  return (
    <Controller
      control={control}
      name={name}
      render={({ field }) => (
        <Rating
          className={className}
          onChange={(_, value) => {
            field.onChange(value ?? field.value);
          }}
          size="large"
          value={field.value}
        />
      )}
    />
  );
}
