import instance from './axios';

interface mbtiType {
  ei: number;
  ns: number;
  tf: number;
  jp: number;
  mint: number;
  pine: number;
  die: number;
}



export async function getRandomFoodList() {
  try {
    const res = await instance.get('/recommend');
    // const res = await axios.get(`${VITE_API_DEV}/recommend`)
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function getRecentRecommendFood() {
  try {
    const res = await instance.get('/mukus/groups/0/recent');
    // const res = await axios.get(`${VITE_API_DEV}/recommend`)
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function getMainRecommendFood(
  groupId: number,
  mbti: mbtiType
) {
  try {
    console.log('랜덤푸드 가져오기:', mbti);
    const res = await instance.get(`/recommend/groups/${groupId}`, {
      params: mbti,
    });
    // const res = await axios.get(`${VITE_API_DEV}/recommend`)
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function getNewRecommendFood(
  groupId: number
) {
  try {
    const res = await instance.get(`/recommend/groups/${groupId}/new`);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function getWeatherRecommendFood(latitude: string, longitude: string) {
  const data = {
    latitude,
    longitude
  }
  try {
    const res = await instance.get('/recommend/weather', {
      params: data
    });
    return res.data;
  } catch (e) {
    console.log(e);
  }
}