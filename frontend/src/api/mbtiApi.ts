import instance from './axios';

interface answerType {
  quizId: string;
  answerId: string;
}

export async function getMbtiQuestionList() {
  try {
    const res = await instance.get('/mbti');
    return res.data.mukBTIQuestions;
  } catch (e) {
    console.log(e);
  }
}

export async function getMbtiResult(answers: answerType[]) {
  const data = { answers };
  try {
    const res = await instance.post('/mbti', data);
    console.log(res);
    return res.data.mukBTIResult;
  } catch (e) {
    console.log(e);
  }
}

export async function postMbtiResult(key: string) {
  const data = { key };
  console.log(data);
  try {
    const res = await instance.post('/users/mbti', data);
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}
