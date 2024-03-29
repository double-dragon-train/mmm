import styles from '../../styles/mainPage/MainPage.module.css';
function MemberCard() {
  return (
    <div className={styles.memberCard}>
      <span className={styles.nickname}>장인턴</span>
      <span className={styles.mbti}>ISFP</span>
    </div>
  );
}

export default MemberCard;
