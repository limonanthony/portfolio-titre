import { z } from 'zod';

import ReviewSchema from '@/classes/schemas/base/Review.schema.ts';

export type ReviewFormSchema = z.infer<typeof ReviewFormStructure.schema>;

export default class ReviewFormStructure {
  public static schema = z.object({
    content: ReviewSchema.content,
    email: ReviewSchema.email.optional(),
    rating: ReviewSchema.rating,
  });

  public static get defaultValues(): ReviewFormSchema {
    return {
      content: '',
      email: undefined,
      rating: 0,
    };
  }
}
