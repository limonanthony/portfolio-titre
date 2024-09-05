import axios, { AxiosError } from 'axios';

export default class RequestHandlers {
  public static fail(error: AxiosError | Error) {
    if (axios.isAxiosError(error)) {
      throw new Error(error.response?.data ?? 'Request failed.');
    }
    throw new Error(error.message);
  }

  public static success<T>(response: T): T {
    return response;
  }
}
