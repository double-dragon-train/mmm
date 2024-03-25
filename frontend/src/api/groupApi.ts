import instance from './axios';

export async function getGroupInfo() {
  try {
    const res = await instance.get('/groups');
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}