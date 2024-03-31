import styles from '../../styles/groupPage/GroupPage.module.css';
import rewardCntIcon from '../../assets/images/rewardCntIcon.png';
import buttonStyles from '../../styles/common/Buttons.module.css';
import { useQuery } from '@tanstack/react-query';
import { getReward } from '../../api/rewardApi';
import userStore from '../../stores/userStore';
import RewardCard from './RewardCard';

interface rewardType {
  id: number;
  condition: string;
  name: string;
  isCleared: boolean;
  imageSrc: string | undefined;
}
function RewardModal() {
  const { groupId } = userStore();
  const {
    data: reward,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['reward'],
    queryFn: () => getReward(groupId),
  });
  console.log('reward:', reward);

  if (isPending) {
    return <div>Loading...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  const rewardCnt = reward.badges.length;
  const myRewardList = reward.badges.filter((reward: rewardType) => {
    return reward.isCleared;
  });
  const myRewardCnt = myRewardList.length;
  const notMyRewardList = reward.badges.filter(
    (reward: rewardType) => {
      return !reward.isCleared;
    }
  );

  return (
    <div className={styles.rewardModalWrapper}>
      <h2>먹적</h2>
      <div>
        <h3 className={styles.myRewardTitle}>보유 먹적</h3>
        <div className={styles.rewardNumBox}>
          <img src={rewardCntIcon} alt="" />
          <span>
            {myRewardCnt}/{rewardCnt}
          </span>
        </div>
        <section className={styles.rewardSection}>
          {myRewardCnt ? 
            myRewardList.map((reward: rewardType) => {
              return (
                <RewardCard
                  id={reward.id}
                  condition={reward.condition}
                  name={reward.name}
                  isCleared={reward.isCleared}
                  imageSrc={reward.imageSrc}
                />
              );
            })
            : <div className={styles.noRewardText}>보유한 먹적이 없습니다.<br />먹적을 달성해보세요!</div>
          }
          
        </section>
      </div>
      <div>
        <h3 className={styles.notMyRewardTitle}>미보유 먹적</h3>
        <section className={styles.rewardSection}>
        {notMyRewardList.map((reward: rewardType) => {
            return (
              <RewardCard
                key={reward.id}
                id={reward.id}
                condition={reward.condition}
                name={reward.name}
                isCleared={reward.isCleared}
                imageSrc={reward.imageSrc}
              />
            );
          })}
        </section>
      </div>
      <button className={buttonStyles.miniRoundedButton}>확인</button>
    </div>
  );
}

export default RewardModal;
