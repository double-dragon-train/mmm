import { useState } from 'react';
import Input from '../components/common/Input';
import styles from '../styles/userPage/UserPage.module.css';
import Button from '../components/common/Button';
import closedEye from '../assets/images/closedEye.png';
import openedEye from '../assets/images/openedEye.png';
import ErrorMessage from '../components/common/ErrorMessage';


function SignupPage() {
  const [isEmailSended, setisEmailSended] = useState<boolean>(false);
  const [isPasswordOpened, setIsPasswordOpened] =
    useState<boolean>(false);

  const checkNickName = () => {};
  const checkEmail = () => {};

  const sendEmail = () => {
    setisEmailSended(!isEmailSended);
  };

  const togglePassword = () => {
    setIsPasswordOpened(!isPasswordOpened);
  };

  return (
    <div className={styles.wrapper}>
      <div className="userTitle">SIGN UP</div>
      <section>
        <div>
          <Input
            title="닉네임"
            info="2~10자 (한, 영(대, 소), 숫자)"
            inputType="text"
            inputWidth="shortInput"
          />
          <Button clickEvent={checkNickName} buttonName="확인" />
        </div>

        <div>
          <Input
            title="이메일"
            info="example@naver.com"
            inputType="text"
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
            inputType={isPasswordOpened ? 'text' : 'password'}
            inputWidth="longInput"
          />
          {isPasswordOpened ? (
            <img onClick={togglePassword} src={openedEye} alt="" />
          ) : (
            <img onClick={togglePassword} src={closedEye} alt="" />
          )}
        </div>
        <div className={styles.inputPassword}>
          <Input
            title={'비밀번호\n확인'}
            info=""
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
      <button className="userButton">완료</button>
    </div>
  );
}

export default SignupPage;
