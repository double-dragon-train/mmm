import instance from './axios';

// 먹보 조회
export const getGroupUserList = async (groupId: number) => {
  try {
    const res = await instance.get(`/groups/${groupId}/users`, {});
    console.log('먹보 조회 res:', res);
    return res.data;
  } catch (e) {
    console.log('먹보 조회 실패:', e);
  }
};

// 먹봇 조회
export const getGroupMukbotList = async (groupId: number) => {
  try {
    const res = await instance.get(`/groups/${groupId}/mukbots`, {});
    console.log('먹봇 조회 res:', res);
    return res.data;
  } catch (e) {
    console.log('먹봇 조회 실패:', e);
  }
};

// 먹보 초대 이메일 검색
export const getEmailFind = async (email: string) => {
  try {
    const res = await instance.get(`/users/${email}`, {});
    console.log('먹보 초대 이메일 검색 res:', res);
    return res.data;
  } catch (e) {
    console.log('먹보 초대 이메일 검색 실패:', e);
  }
};
