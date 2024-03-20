import instance from './axios';

// 닉네임 중복 확인
export const getNicknameValidate = async (nickname: string) => {
  try {
    const res = await instance.get(`/users/${nickname}`);
    console.log('닉네임 res:', res);
    return res;
  } catch (e) {
    console.log('닉네임 중복 확인 실패:', e);
  }
};

// 회원가입
type SignupData = {
  email: string;
  password: string;
  passwordConfirm: string;
  nickname: string;
};
export const postSignup = async (signupData: SignupData) => {
  try {
    const res = await instance.post('users/join', signupData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    console.log('회원가입 성공:', res, signupData);
  } catch (e) {
    console.log('회원가입 실패:', e);
  }
};

// 로그인
type LoginData = {
  email: string;
  password: string;
};
export const postLogin = async (loginData: LoginData) => {
  try {
    const res = await instance.post('users/login', loginData, {
    });
    console.log('로그인 성공:', res, loginData);
  } catch (e) {
    console.log('로그인 실패:', e);
  }
};
