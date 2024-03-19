import { useState } from 'react';
import Input from '../components/common/Input';
import styles from '../styles/userPage/UserPage.module.css';
import Button from '../components/common/Button';
import subLogo from '../assets/images/subLogo.png';
import closedEye from '../assets/images/closedEye.png';
import openedEye from '../assets/images/openedEye.png';
import { Link } from 'react-router-dom';
import {
  checkEmailValidation,
  checkNicknameValidation,
  checkPasswordValidation,
} from '../utils/validation';

function SignupPage() {
  const [inputList, setInputList] = useState({
    nickname: '',
    email: '',
    code: '',
    password: '',
    passwordConfirm: '',
  });
  const { nickname, email, code, password, passwordConfirm } =
    inputList;

  const changeInputList = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    const { name, value } = e.target;
    setInputList({
      ...inputList,
      [name]: value,
    });
    console.log(name, value);
  };
  const [isEmailSended, setIsEmailSended] = useState<boolean>(false);
  const [isPasswordOpened, setIsPasswordOpened] =
    useState<boolean>(false);

  const [isNicknameValid, setIsNicknameValid] =
    useState<boolean>(true);
  const checkNickName = () => {
    setIsNicknameValid(checkNicknameValidation(nickname));
  };

  const [isEmailValid, setIsEmailValid] = useState<boolean>(true);
  const checkEmail = () => {
    setIsEmailValid(checkEmailValidation(email));
  };

  const [isPasswordValid, setIsPasswordValid] =
    useState<boolean>(true);

  const checkPassword = () => {
    setIsPasswordValid(checkPasswordValidation(password));
  };

  const [isPasswordConfirmValid, setIsPasswordConfirmValid] =
    useState<boolean>(true);

  const checkPasswordConfirm = () => {
    if (password !== passwordConfirm)
      setIsPasswordConfirmValid(false);
    else setIsPasswordConfirmValid(true);
  };

  const sendEmail = () => {
    setIsEmailSended(!isEmailSended);
  };

  const togglePassword = () => {
    setIsPasswordOpened(!isPasswordOpened);
  };

  return (
    <div className={styles.wrapper}>
      <Link to="/">
        <img className={styles.subLogo} src={subLogo} alt="" />
      </Link>
      {/* <div className="userTitle">SIGN UP</div> */}
      <section>
        <div>
          <Input
            title="닉네임"
            info="2~10자 (한, 영(대, 소), 숫자)"
            inputName="nickname"
            inputValue={nickname}
            onChange={changeInputList}
            inputType="text"
            inputWidth="shortInput"
            onBlur={checkNickName}
            isInputValid={isNicknameValid}
            errorMessage="닉네임 형식이 잘못되었습니다."
          />
          <Button clickEvent={checkNickName} buttonName="확인" />
        </div>

        <div>
          <Input
            title="이메일"
            info="example@naver.com"
            inputName="email"
            inputValue={email}
            onChange={changeInputList}
            inputType="text"
            inputWidth="shortInput"
            onBlur={checkEmail}
            isInputValid={isEmailValid}
            errorMessage="이메일 형식이 잘못되었습니다."
          />
          <Button clickEvent={sendEmail} buttonName="인증" />
        </div>
        {isEmailSended && (
          <div className="inputContainer">
            <label></label>
            <div className="inputBox">
              <input
                type="text"
                name="code"
                value={code}
                onChange={changeInputList}
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
            inputName="password"
            inputValue={password}
            onChange={changeInputList}
            inputType={isPasswordOpened ? 'text' : 'password'}
            inputWidth="longInput"
            onBlur={checkPassword}
            isInputValid={isPasswordValid}
            errorMessage="비밀번호 형식이 잘못되었습니다."
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
            inputName="passwordConfirm"
            inputValue={passwordConfirm}
            onChange={changeInputList}
            inputType={isPasswordOpened ? 'text' : 'password'}
            inputWidth="longInput"
            onBlur={checkPasswordConfirm}
            isInputValid={isPasswordConfirmValid}
            errorMessage="비밀번호를 다시 확인해주세요."
          />
          {isPasswordOpened ? (
            <img onClick={togglePassword} src={openedEye} alt="" />
          ) : (
            <img onClick={togglePassword} src={closedEye} alt="" />
          )}
        </div>
      </section>
      {/* <ErrorMessage
        errorFontSize="bigErrorMessage"
        errorTarget="닉네임"
      /> */}
      <button className="userButton">완료</button>
    </div>
  );
}

export default SignupPage;
