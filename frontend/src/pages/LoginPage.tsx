import { useState } from 'react';
import Input from '../components/common/Input';
import '../styles/common/buttons.css';
import '../styles/common/userTitle.css';
import styles from '../styles/userPage/UserPage.module.css';
import closedEye from '../assets/images/closedEye.png';
import openedEye from '../assets/images/openedEye.png';
import { Link } from 'react-router-dom';

function LoginPage() {
  const [isPasswordOpened, setIsPasswordOpened] = useState<boolean>(false);
  console.log(setIsPasswordOpened)
  return (
    <div className={styles.wrapper}>
      <div className="userTitle">LOGIN</div>
      <section>
        <Input
          title="이메일"
          info="example@naver.com"
          inputWidth="longInput"
        />
        <div className={styles.inputPassword}>
          <Input
            title="비밀번호"
            info={
              '8~20자\n(한, 영(대, 소), 숫자, 특수문자 각 1글자 이상)'
            }
            inputWidth="longInput"
          />
          {isPasswordOpened ? (
            <img src={closedEye} alt="" />
          ) : (
            <img src={openedEye} alt="" />
          )}
        </div>
      </section>
      <button className="userButton">로그인</button>
      <span>
        <Link to='/signup'>기존 회원이 아니신가요?</Link>
      </span>
    </div>

  );
}

export default LoginPage;
