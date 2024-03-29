import styles from '../../styles/mainPage/MainPage.module.css';
import reroll from '../../assets/images/reroll.png';
import { useQuery } from '@tanstack/react-query';
import { getMainRecommendFood } from '../../api/recommendApi';
import alert from '../../assets/images/alert.png';
import userStore from '../../stores/userStore';

interface propsType {
  groupId: number;
}
function MainRecommendSection({ groupId }: propsType) {
  const { mbti } = userStore();

  const {
    data: mainRecommendFoodList,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['main'],
    queryFn: () => getMainRecommendFood(groupId, mbti),
  });
  console.log(mainRecommendFoodList)
  if (isPending) {
    return <div>isLoding...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  return (
    <section className={styles.mainRecommendSection}>
      <header className={styles.header}>

        <div className={styles.titleBox}>
          <h2>추천메뉴</h2>
          <img src={alert} alt="" />
        </div>
        <img src={reroll} alt="" />
      </header>
      <main>
        <div className={styles.recommendFoodBox}>
          <img src="" alt="" />
          <span>진라면 순한맛</span>
        </div>
      </main>
    </section>
  );
}

export default MainRecommendSection;
