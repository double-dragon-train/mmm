import styles from '../../styles/groupPage/GroupPage.module.css';
import log from '../../assets/images/log.png';
import { useState } from 'react';
import LogBox from './LogBox';

function Header() {
  const [isLogOpen, setIsLogOpen] = useState(false);

  const toggleIsLogOpen = () => {
    setIsLogOpen(!isLogOpen)
  }
  return (
    <div className={styles.header}>
      <div className={styles.titleBox}>
        <span>먹그룹</span>
      </div>
      <div className={styles.logBox} onClick={toggleIsLogOpen}>
        <img src={log} alt="" />
        <span>먹로그</span>
      </div>
      {isLogOpen && <LogBox />}
    </div>
  );
}

export default Header;
