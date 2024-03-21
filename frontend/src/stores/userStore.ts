import { create } from 'zustand';

interface answerType {
  quizId: string;
  answerId: string;
  
}

interface userStoreType {
    answerList: answerType[];
    addAnswerList: (value: answerType[]) => void;
    accessToken: string;
  }
const userStore = create<userStoreType>((set) => ({
    accessToken: '',
  //   setAccessToken: (value) => set({ accessToken: value }),
  //   loginModalOpen: false,
  //   setLoginModalOpen: (value) => set({ loginModalOpen: value }),
  //   isMyPage: true,
  //   setIsMyPage: (value) => set({ isMyPage: value }),
  //   isLogin: false,
  //   setIsLogin: (value) => set({ isLogin: value }),
  //   currentPageID: undefined,
  //   setCurrentPageID: (value) => set({ currentPageID: value }),
  //   loginAccount: {},
  //   setLoginAccount: (value) => set({ loginAccount: value }),
  //   followingUsers: [],
  //   setFollowingUsers: (value) => set({ followingUsers: value }),

  answerList: [],
  addAnswerList: (value: answerType[]) => set({ answerList: value }),
}));

export default userStore;
