import instance from './axios';

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
