import { useState } from 'react';
import Input from '../components/common/Input';
import '../styles/common/buttons.css';
import '../styles/common/userTitle.css';
import styles from '../styles/userPage/UserPage.module.css';
import Button from '../components/common/Button';
import closedEye from '../assets/images/closedEye.png';
import openedEye from '../assets/images/openedEye.png';

function SignupPage() {
  const [isEmailSended, setisEmailSended] = useState(false);
  const [isPasswordOpened, setIsPasswordOpened] = useState(false);

  const checkNickName = () => {};
  const checkEmail = () => {};

  const sendEmail = () => {
    setisEmailSended(!isEmailSended);
  };
  return (
    <div className={styles.wrapper}>
      <div className="userTitle">SIGN UP</div>
      <section>
        <div>
          <Input
            title="닉네임"
            info="2~10자 (한, 영(대, 소), 숫자)"
            inputWidth="shortInput"
          />
          <Button clickEvent={checkNickName} buttonName="확인" />
        </div>

        <div>
          <Input
            title="이메일"
            info="example@naver.com"
            inputWidth="shortInput"
          />
          <Button clickEvent={sendEmail} buttonName="인증" />
        </div>
        {isEmailSended && (
          <div className="inputContainer">
            <label></label>
            <div className="inputBox">
              <input
                type="text"
                className="shortInput"
                placeholder="인증번호"
              />
              <span></span>
            </div>
            <Button clickEvent={checkEmail} buttonName="확인" />
          </div>
        )}

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

        <div className={styles.inputPassword}>
          <Input
            title={'비밀번호\n확인'}
            info={''}
            inputWidth="longInput"
          />
          {isPasswordOpened ? (
            <img src={closedEye} alt="" />
          ) : (
            <img src={openedEye} alt="" />
          )}
        </div>
      </section>
      <button className="userButton">완료</button>
    </div>
  );
}

export default SignupPage;
