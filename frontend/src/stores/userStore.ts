import { create } from 'zustand';
import { persist } from 'zustand/middleware';

interface answerType {
  quizId: string;
  answerId: string;
}

interface mbtiType {
  ei: number;
  ns: number;
  tf: number;
  jp: number;
  mint: number;
  pine: number;
  die: number;
}

interface userStoreType {
  answerList: answerType[];
  updateAnswerList: (value: answerType[]) => void;
  accessToken: string;
  setAccessToken: (value: string) => void;
  refreshToken: string;
  setRefreshToken: (value: string) => void;
  isLogin: boolean;
  setIsLogin: (value: boolean) => void;
  mbtiKey: string;
  setMbtiKey: (value: string) => void;
  mbti: mbtiType;
  setMbti: (value: mbtiType) => void;
}
const userStore = create(
  persist<userStoreType>(
    (set) => ({
      accessToken: '',
      setAccessToken: (value: string) => set({ accessToken: value }),
      refreshToken: '',
      setRefreshToken: (value: string) =>
        set({ refreshToken: value }),
      isLogin: false,
      setIsLogin: (value: boolean) => set({ isLogin: value }),
      answerList: [],
      updateAnswerList: (value: answerType[]) =>
        set({ answerList: value }),
      mbtiKey: '',
      setMbtiKey: (value: string) => set({ mbtiKey: value }),
      mbti: { ei: 0, ns: 0, tf: 0, jp: 0, mint: 0, pine: 0, die: 0 },
      setMbti: (value: mbtiType) => set({ mbti: value }),
      //   loginModalOpen: false,
      //   setLoginModalOpen: (value) => set({ loginModalOpen: value }),
      //   isMyPage: true,
      //   setIsMyPage: (value) => set({ isMyPage: value }),
      //   currentPageID: undefined,
      //   setCurrentPageID: (value) => set({ currentPageID: value }),
      //   loginAccount: {},
      //   setLoginAccount: (value) => set({ loginAccount: value }),
      //   followingUsers: [],
      //   setFollowingUsers: (value) => set({ followingUsers: value }),
    }),
    { name: 'userData' }
  )
);

export default userStore;
