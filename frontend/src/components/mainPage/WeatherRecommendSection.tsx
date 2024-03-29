import styles from '../../styles/mainPage/MainPage.module.css';
import jammin from '../../assets/images/jammin.png';

function WeatherRecommendSection() {
  return (
    <section className={styles.weatherRecommendSection}>
      <div className={styles.textBox}>
        <h2>날씨별 추천</h2>
        <span>비도 오는데<br/>혹시 <span className={styles.foodName}>[ 마라탕 ]</span> 어때요?</span>
      </div>
      <img src={jammin} alt="" />
    </section>
  );
}

export default WeatherRecommendSection;
