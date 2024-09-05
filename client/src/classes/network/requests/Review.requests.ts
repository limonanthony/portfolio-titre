import apiManager from '@/classes/network/Api.manager.ts';
import { ReviewFormSchema } from '@/classes/schemas/form/Review.form.structure.ts';
import API_URL from '@/constants/const/api.url.ts';

export default class ReviewRequests {
  static async create(data: ReviewFormSchema) {
    console.debug(JSON.stringify(data));
    return apiManager.instance.post('/review', { ...data, authorId: null });
  }

  static async getAll() {
    return apiManager.instance.get(`${API_URL}/reviews`);
  }
}
