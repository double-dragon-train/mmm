import styles from '../../styles/common/Buttons.module.css';

interface ButtonProps {
  clickEvent: () => void;
  buttonName: string;
}

function MiniRedButton ({ clickEvent, buttonName }: ButtonProps) {
  return (
    <button onClick={clickEvent} className={styles.miniRedButton}>
      {buttonName}
    </button>
  );
}

export default MiniRedButton;
