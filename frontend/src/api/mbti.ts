import instance from './axios';

export async function getMbtiQuestionList() {
  try {
    const res = await instance.get('/mbti');
    // console.log(res);
    return res.data.questions;
  } catch (e) {
    console.log(e);
  }
}
