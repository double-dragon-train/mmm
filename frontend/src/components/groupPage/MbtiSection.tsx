import styles from '../../styles/groupPage/GroupPage.module.css';

function MbtiSection() {
  return (
    <section className={styles.mbtiSection}>
      <div className={styles.mbtiTitleBox}>
        <span>먹BTI</span>
        <div>ISTP</div>
      </div>
      <div className={styles.mbtiBarBox}>
        <div className={styles.mbtiNameBox}>
          <span>Ignite</span>
          <span>맵당당</span>
        </div>
        <div>
          <div></div>
        </div>
        <div className={styles.mbtiNameBox}>
          <span>Emergency</span>
          <span>맵찌질</span>
        </div>
      </div>
    </section>
  );
}

export default MbtiSection;
