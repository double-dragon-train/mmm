import instance from './axios';

interface answerType {
  quizId: string;
  answerId: string;
}

export async function getMbtiQuestionList() {
  try {
    const res = await instance.get('/mbti');
    console.log(res);
    return res.data.questions;
  } catch (e) {
    console.log(e);
  }
}

export async function getMbtiResult(answerList: answerType[]) {
  try {
    const res = await instance.post('/mbti', answerList);
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}
