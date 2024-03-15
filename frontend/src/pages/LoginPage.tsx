import { useState } from 'react';
import Input from '../components/common/Input';
import '../styles/common/buttons.css';
import '../styles/common/userTitle.css';
import styles from '../styles/userPage/UserPage.module.css';
import closedEye from '../assets/images/closedEye.png';
import openedEye from '../assets/images/openedEye.png';
import { Link } from 'react-router-dom';
import ErrorMessage from '../components/common/ErrorMessage';

function LoginPage() {
  const [isPasswordOpened, setIsPasswordOpened] =
    useState<boolean>(false);

  const togglePassword = () => {
    setIsPasswordOpened(!isPasswordOpened);
  };

  return (
    <div className={styles.wrapper}>
      <div className="userTitle">LOGIN</div>
      <section>
        <Input
          title="이메일"
          info="example@naver.com"
          inputType="text"
          inputWidth="longInput"
        />
        <div className={styles.inputPassword}>
          <Input
            title="비밀번호"
            info={
              '8~20자\n(한, 영(대, 소), 숫자, 특수문자 각 1글자 이상)'
            }
            inputType={isPasswordOpened ? 'text' : 'password'}
            inputWidth="longInput"
          />
          {isPasswordOpened ? (
            <img onClick={togglePassword} src={openedEye} alt="" />
          ) : (
            <img onClick={togglePassword} src={closedEye} alt="" />
          )}
        </div>
      </section>
      <ErrorMessage
        errorFontSize="bigErrorMessage"
        errorTarget="닉네임"
      />
      <button className="userButton">로그인</button>
      <span>
        <Link to="/signup">기존 회원이 아니신가요?</Link>
      </span>
    </div>
  );
}

export default LoginPage;
