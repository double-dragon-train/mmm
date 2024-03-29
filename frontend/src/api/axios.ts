import axios from 'axios';

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
      // // 테스트
      // config.headers['Authorization'] =
      //   'Bearer eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3RlckBuYXZlci5jb20iLCJpYXQiOjE3MTE1OTE0MTUsImV4cCI6MTcxMTU5MzIxNX0.h8K_tSXaX3FwIUPi2nsgnhAMKwNr0flctJyjtfquYek';
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
    console.log(error)
    // if (error.response && error.response?.status ===  500) {
    //   const refreshTokenData = await getRefreshToken();
    //   console.log('error.response: ', error.response);
    //   console.log('refreshToken:', refreshTokenData);
    //   if (refreshTokenData) {
    //     const newAccessToken = refreshTokenData.accessToken;
    //     error.config.headers.Authorization = newAccessToken;
    //     return instance(error.config);
    //   } else {
    //     console.log('axios.ts 토큰 재발급 실패');
    //   }
    // }
    // console.log('response에러 :', error);
  }
);

// 토큰 재발급
// const getRefreshToken = async () => {
//   //
//   const userDataString = localStorage.getItem('userData');

//   if (userDataString !== null) {
//     // JSON 형식으로 파싱하기
//     const userData = JSON.parse(userDataString);

//     // refreshToken에 접근
//     const refreshToken = userData.state.refreshToken;
//     console.log('refreshToken:', refreshToken);
//     try {
//       const res = await instance.get('/users/reissue', {
//         headers: {
//           requestToken: refreshToken,
//         },
//       });
//       console.log('토큰 재발급 성공:', res);
//       return res.data;

//       //
//     } catch (e) {
//       console.log('토큰 재발급 실패:', e);
//       console.log('토큰 재발급 실패:', refreshToken);
//     }
//   } else {
//     console.error('userData가 null입니다.');
//   }
// };

export default instance;
