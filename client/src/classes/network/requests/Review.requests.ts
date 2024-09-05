import apiManager from '@/classes/network/Api.manager.ts';
import API_URL from '@/constants/const/api.url.ts';

export default class ReviewRequests {
  static async create(data: any) {
    return apiManager.instance.post(`${API_URL}/review`, {
      data,
    });
  }

  static async getAll() {
    return apiManager.instance.get(`${API_URL}/reviews`);
  }
}
