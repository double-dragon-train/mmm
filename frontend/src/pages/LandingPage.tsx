import { Link } from 'react-router-dom';
import styles from '../styles/landingPage/LandingPage.module.css';
import '../styles/common/buttons.css';
import mainLogo from '../assets/images/mainLogo.png';
import dakbal from '../assets/images/dakbal.jpg';

function LandingPage() {
  
  return (
    <div className={styles.wrapper}>
      <img className={styles.mainLogo} src={mainLogo} alt="" />
      <img className={styles.randomFood} src={dakbal} alt="" />
      <div>
        <Link to="/login">
          <button className="landingButton">로그인</button>
        </Link>
        <Link to="/mbti/1">
          <button className="landingButton">먹BTI 검사</button>
        </Link>
      </div>
    </div>
  );
}

export default LandingPage;
