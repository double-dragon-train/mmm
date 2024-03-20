import instance from './axios';

// 닉네임 중복 확인
export const getNicknameValidate = async (nickname: string) => {
  try {
    const res = await instance.get(`/users/${nickname}`);
    console.log('res:', res);
    return res;
  } catch (e) {
    console.log('닉네임 중복 확인 실패:', e);
  }
};

// 회원가입
type UserData = {
  email: string;
  password: string;
  passwordConfirm: string;
  nickname: string;
};
export const postSignup = async (userData: UserData) => {
  try {
    const res = await instance.post('users/join', userData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    console.log('회원가입 성공:', res, userData);
  } catch (e) {
    console.log('회원가입 실패:', e);
  }
};


