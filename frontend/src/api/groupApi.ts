import instance from './axios';

export async function getGroupInfo() {
  try {
    const res = await instance.get('/groups', {});
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function postGroupInfo(name: string) {
  const data = { name };
  const form = { data: data };
  console.log(form);
  // console.log(name);
  try {
    const res = await instance.post('/groups', form, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    }
);
    console.log('결과는!', res);
    return res;
  } catch (e) {
    console.log(e);
  }
}
