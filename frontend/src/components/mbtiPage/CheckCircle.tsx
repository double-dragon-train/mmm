import styles from '../../styles/mbtiPage/MbtiPage.module.css';

interface propsType {
  handleSelectAnswer: () => void;
  isSelected: boolean;
}
function CheckCircle({ handleSelectAnswer, isSelected }: propsType) {
  return (
    <div
      className={styles.parentCheckCircle}
      onClick={handleSelectAnswer}
    >
      <div className={styles.circleLine} />
      <div
        className={
          isSelected
            ? styles.selectedChildCheckCircle
            : styles.childCheckCircle
        }
      ></div>
    </div>
  );
}

export default CheckCircle;
