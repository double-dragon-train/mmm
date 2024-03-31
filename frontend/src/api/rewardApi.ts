import instance from './axios';

export async function getReward(groupId: number) {
  try {
    const res = await instance.get(`/groups/${groupId}/badges`);
    console.log('먹적:',res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}