import axios from 'axios';
import { getRefreshToken } from './userApi';

const { VITE_API_DEV } = import.meta.env;

const instance = axios.create({
  baseURL: VITE_API_DEV,
  withCredentials: true,
});

instance.interceptors.request.use(
  (config) => {
    const userDataString = localStorage.getItem('userData');
    if (userDataString !== null) {
      const userData = JSON.parse(userDataString);
      const accessToken = userData.state.accessToken;
      console.log('accesstoken: ', accessToken);
      config.headers['Authorization'] = accessToken;
    } else {
      console.log('userData가 null입니다.');
    }

    return config;
  },
  (error) => {
    console.log('config 에러: ', error);
    return Promise.reject(error);
  }
);
instance.interceptors.response.use(
  async (response) => {
    return response;
  },
  async (error) => {
    if (error.response && error.response?.status === 500) {
      const refreshTokenData = await getRefreshToken();
      console.log('refreshToken:', refreshTokenData);
      if (refreshTokenData) {
        const newAccessToken = refreshTokenData.accessToken;
        error.config.headers.Authorization = newAccessToken;
        return instance(error.config);
      } else {
        console.log('axios.ts 토큰 재발급 실패');
      }
    }
    console.log('response에러 :', error);
  }
);

export default instance;
