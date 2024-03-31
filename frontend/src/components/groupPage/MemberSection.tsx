import { useEffect, useState } from 'react';
import {
  getEmailFind,
  getGroupMukbotList,
  getGroupUserList,
} from '../../api/memberApi';
import styles from '../../styles/groupPage/MemberSection.module.css';
import SubMemberArticle from '../common/SubMemberArticle';
import { useQuery } from '@tanstack/react-query';
import SubMemberCard from '../common/SubMemberCard';
import Modal from '../common/Modal';
import Input from '../common/Input';
import {
  checkEmailValidation,
  checkNicknameValidation,
} from '../../utils/validation';
import finder from '../../assets/images/finder.png';

interface propsType {
  groupId: number;
}

function MemberSection({ groupId }: propsType) {
  // 먹보 조회 api
  const { data: groupUserList, isPending: isUserPending } = useQuery({
    queryKey: ['groupUserList'],
    queryFn: () => getGroupUserList(groupId),
  });

  useEffect(() => {
    if (!isUserPending && groupUserList) {
      console.log(
        'groupUserInfo.users.length: ',
        groupUserList.users.length
      );
      console.log(groupUserList.users[0].name);
    }
  }, [isUserPending, groupUserList]);

  // 먹봇 조회 api
  const { data: groupMukbotList, isPending: isMukbotPending } =
    useQuery({
      queryKey: ['groupMukbotList'],
      queryFn: () => getGroupMukbotList(groupId),
    });

  useEffect(() => {
    if (!isMukbotPending && groupMukbotList) {
    }
  }, [isMukbotPending, groupMukbotList]);

  // 먹보 초대 이메일 검색 api
  const { refetch } = useQuery({
    queryKey: ['mukboInfo'],
    queryFn: () => getEmailFind(email),
    enabled: false,
  });

  const handleEmailFind = () => {
    refetch().then((res) => {
      if (res) {
        console.log(email);
        // console.log('먹보 초대 이메일 검색: ', res);
      } else {
        console.log('응답 객체 또는 데이터가 존재하지 않습니다.');
      }
    });
  };

  const [isMukboInviteModalOpen, setIsMukboInviteModalOpen] =
    useState(false);
  const [isMukbotMakeModalOpen, setIsMukbotMakeModalOpen] =
    useState(false);

  const handleMukboInviteOpenModal = () => {
    setIsMukboInviteModalOpen(true);
  };
  const handleMukbotMakeOpenModal = () => {
    setIsMukbotMakeModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsMukboInviteModalOpen(false);
    setIsMukbotMakeModalOpen(false);
  };

  const [inputList, setInputList] = useState({
    nickname: '',
    email: '',
  });
  const { nickname, email } = inputList;
  // const inputValues = Object.values(inputList);

  const changeInputList = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    const { name, value } = e.target;
    setInputList({
      ...inputList,
      [name]: value,
    });
    console.log(email);
  };

  const [isEmailValid, setIsEmailValid] = useState<boolean>(true);
  const checkEmail = () => {
    setIsEmailValid(checkEmailValidation(email));
  };

  const [isNicknameValid, setIsNicknameValid] =
    useState<boolean>(true);
  const checkNickName = () => {
    setIsNicknameValid(checkNicknameValidation(nickname));
  };

  return (
    <section className={styles.memberSection}>
      <span>구성원</span>
      <SubMemberArticle
        articleName="먹보"
        modalButton="+ 초대"
        clickEvent={handleMukboInviteOpenModal}
      >
        <div className={styles.memberCardBox}>
          {groupUserList &&
            groupUserList.users.length > 0 &&
            groupUserList.users.map(
              (user: { name: string; mukBTI: string }) => (
                <SubMemberCard
                  articleName="먹보"
                  memberName={user.name}
                  memberMBTI={user.mukBTI}
                  buttonName="추방"
                />
              )
            )}
          {groupUserList &&
            groupUserList.users &&
            groupUserList.users.length === 0 && (
              <span>
                가입 사용자는 <br />
                먹보로 초대해보세요.
              </span>
            )}
        </div>
      </SubMemberArticle>
      {isMukboInviteModalOpen && (
        <Modal clickEvent={handleCloseModal}>
          <div className={styles.modal}>
            <header>먹보 초대</header>
            <article>
              <div className={styles.emailInput}>
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
                <img
                  className={styles.finder}
                  onClick={handleEmailFind}
                  src={finder}
                  alt="finder"
                />
              </div>
              <Input
                title="닉네임"
                info="2~10자 (한글, 영어(대/소), 숫자)"
                inputName="nickname"
                inputValue={nickname}
                onChange={changeInputList}
                inputType="text"
                inputWidth="shortInput"
                onBlur={checkNickName}
                isInputValid={isNicknameValid}
                errorMessage="닉네임 형식이 잘못되었습니다."
              />
            </article>
          </div>
        </Modal>
      )}
      <SubMemberArticle
        articleName="먹봇"
        modalButton="+ 생성"
        clickEvent={handleMukbotMakeOpenModal}
      >
        <div className={styles.memberCardBox}>
          {groupMukbotList &&
            groupMukbotList.users.length > 0 &&
            groupMukbotList.users.map(
              (user: { name: string; mukBTI: string }) => (
                <SubMemberCard
                  articleName="먹봇"
                  memberName={user.name}
                  memberMBTI={user.mukBTI}
                  buttonName="추방"
                />
              )
            )}
          {!groupMukbotList && <div>로딩 중...</div>}
          {groupMukbotList &&
            groupMukbotList.users &&
            groupMukbotList.users.length === 0 && (
              <span>
                미가입 사용자는 <br />
                먹봇으로 생성해보세요.
              </span>
            )}
        </div>
      </SubMemberArticle>
      {isMukbotMakeModalOpen && (
        <Modal clickEvent={handleCloseModal}>
          <div></div>
        </Modal>
      )}
    </section>
  );
}

export default MemberSection;
