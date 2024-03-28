import styles from '../../styles/mainPage/MainPage.module.css';
import reroll from '../../assets/images/reroll.png'

function MainRecommendSection() {
  return (
    <section className={styles.recommendSection}>
      <header className={styles.header}>
        <h2>gdgd</h2>
        <img src={reroll} alt="" />
      </header>
    </section>
  );
}

export default MainRecommendSection;
