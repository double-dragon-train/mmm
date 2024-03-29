import styles from '../../styles/mainPage/MainPage.module.css';
import jammin from '../../assets/images/jammin.png';

function NewRecommendSection() {
  return (
    <section className={styles.newRecommendSection}>
      <div className={styles.textBox}>
        <h2>신메뉴 추천</h2>
        <span>
          오늘은 새로운 메뉴를 시도해보세요!
          <br />
          혹시 <span className={styles.foodName}>[ 마라탕 ]</span> 어때요?
        </span>
      </div>
      <img src={jammin} alt="" />
    </section>
  );
}

export default NewRecommendSection;
