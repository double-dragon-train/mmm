import styles from '../../styles/groupPage/GroupPage.module.css';
import log from '../../assets/images/log.png';

function Header() {
  return (
    <div className={styles.header}>
      <div className={styles.titleBox}>
        <span>먹그룹</span>
      </div>
      <div className={styles.logBox}>
        <img src={log} alt="" />
        <span>먹로그</span>
      </div>
    </div>
  );
}

export default Header;
