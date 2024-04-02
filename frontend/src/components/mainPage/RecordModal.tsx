// import { useQuery } from '@tanstack/react-query';
import styles from '../../styles/mainPage/MainPage.module.css';
import buttonStyles from '../../styles/common/Buttons.module.css';
import { getRecentRecommendFood } from '../../api/recommendApi';
import { useQuery } from '@tanstack/react-query';
// import { getRecentRecommendFood } from '../../api/recommendApi';

interface propsType {
    handleCloseModal: () => void;
    handleCreateRecord: () => void;
    groupId: number;
}
function RecordModal({ handleCloseModal, handleCreateRecord, groupId }: propsType) {
  const { data: recentFoodList, isPending, isError } = useQuery({
      queryKey: ['recentRecommendFood'],
      queryFn: () => getRecentRecommendFood(groupId)
  })

  if (isPending) {
    return <div>Loading...</div>
  }
  if (isError) {
    return <div>error</div>
  }
  console.log(recentFoodList)
  return (
    <div className={styles.recordModalWrapper}>
      <h2>먹기록</h2>
      <p>
        2024/03/12(월)에 추천받은 메뉴 중<br />
        <span className={styles.boldWord}>어떤 메뉴</span>를 드셨나요?
      </p>
      <section className={styles.recommendBox}>
        <div className={styles.recommendItem}>
          <img src="" alt="" />
          <span>김치찌개</span>
        </div>
      </section>
      <div className={styles.buttonBox}>
        <button
          onClick={handleCreateRecord}
          className={buttonStyles.miniRoundedButton}
        >
          확인
        </button>
        <button
          onClick={handleCloseModal}
          className={buttonStyles.miniRoundedButton}
        >
          없어요
        </button>
      </div>
    </div>
  );
}

export default RecordModal;
