import { useQuery } from '@tanstack/react-query';
import { getGroupMbti } from '../../api/mbtiApi';
import styles from '../../styles/mainPage/MainPage.module.css';
import buttonStyles from '../../styles/common/Buttons.module.css';
import userStore from '../../stores/userStore';
import { useEffect } from 'react';
import MemberCard from './MemberCard';

interface propsType {
  hadleOpenCreateModal: () => void;
  isSolo: boolean;
}
function GroupSection({
  hadleOpenCreateModal,
  isSolo,
}: propsType) {
  const { setMbti } = userStore();
  const {
    data: groupMbti,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['groupMbti'],
    queryFn: getGroupMbti,
  });

  useEffect(() => {
    setMbti(groupMbti);
  }, []);
  if (isPending) {
    return <div>isLoding...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  return (
    <div className={styles.groupSection}>
      <div className={styles.header}>
        <h2>먹그룹</h2>
      </div>
      <div className={styles.groupInfoBox}>
        <div>그룹명</div>
        <div>먹BTI</div>
      </div>
      {isSolo ? (
        <main className={styles.soloMain}>
          <span>
            부서 내 팀원들을 초대하여 먹그룹을 만들어
            <br />
            대표 먹BTI를 설정해보세요.
          </span>
          <button
            className={buttonStyles.squaredButton}
            onClick={hadleOpenCreateModal}
          >
            먹그룹 만들기
          </button>
        </main>
      ) : (
        <main className={styles.groupMain}>
          <article>
            <h2 className={styles.todayTitle}>오늘 같이 먹어요</h2>
            <div>
              <MemberCard />
            </div>
          </article>
          <article>
            <h2 className={styles.todayNext}>아쉽지만 다음에...</h2>
            <div>
              <MemberCard />
            </div>
          </article>
        </main>
      )}
    </div>
  );
}

export default GroupSection;
