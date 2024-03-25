import { create } from 'zustand';
import { persist } from 'zustand/middleware';

interface answerType {
  quizId: string;
  answerId: string;
}

interface userStoreType {
  answerList: answerType[];
  addAnswerList: (value: answerType[]) => void;
  accessToken: string;
  setAccessToken: (value: string) => void;
  isLogin: boolean;
  setIsLogin: (value: boolean) => void;
}
const userStore = create(
  persist<userStoreType>(
    (set) => ({
      accessToken: '',
      setAccessToken: (value: string) => set({ accessToken: value }),
      isLogin: false,
      setIsLogin: (value: boolean) => set({ isLogin: value }),
      answerList: [],
      addAnswerList: (value: answerType[]) =>
        set({ answerList: value }),
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
    { name: 'accessToken' }
  )
);

export default userStore;
