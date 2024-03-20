import styles from '../../styles/mbtiPage/MbtiPage.module.css';

function CheckCircle() {
  return (
    <div className={styles.parentCheckCircle}>
      <div className={styles.childCheckCircle}></div>
    </div>
  );
}

export default CheckCircle;
