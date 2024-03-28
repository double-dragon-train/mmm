import instance from './axios';

export async function getGroupInfo(accessToken: string) {
  try {
    const res = await instance.get('/groups', {
      headers: {
          Authorization: accessToken,
        },
    });
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}