import { z } from 'zod';

export default class ReviewSchema {
  static content = z
    .string({
      // eslint-disable-next-line sonarjs/no-duplicate-string
      invalid_type_error: 'The content is required.',
      required_error: 'The content is required.',
    })
    .min(1, {
      message: 'The content is required.',
    })
    .max(254, {
      message: 'The content cannot be over 255.',
    });

  static email = z
    .string({
      invalid_type_error: 'The email is required.',
      required_error: 'The email is required.',
    })
    .email({
      message: 'The email is invalid.',
    });

  static rating = z
    .number({
      invalid_type_error: 'The rating is required.',
      required_error: 'The rating is required.',
    })
    .min(1, {
      message: 'The rating cannot be under 1.',
    })
    .max(5, {
      message: 'The rating cannot be over 5.',
    });
}
