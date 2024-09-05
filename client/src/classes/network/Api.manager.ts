import axios, { AxiosInstance } from 'axios';

import RequestHandlers from '@/classes/network/Request.handlers.ts';
import API_URL from '@/constants/const/api.url.ts';

class ApiManager {
  public instance: AxiosInstance;

  constructor() {
    this.instance = axios.create({
      baseURL: API_URL,
    });
    this.setErrorHandling();
  }

  private setErrorHandling() {
    this.instance.interceptors.response.use(RequestHandlers.success, RequestHandlers.fail);
  }

  public setToken(token: string) {
    this.instance.defaults.headers.Authorization = `Bearer ${token}`;
  }
}

export default new ApiManager();
