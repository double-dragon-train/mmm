import ProfileImgBox from '../common/ProfileImgBox';
import styles from '../../styles/groupPage/GroupPage.module.css';
import { useState } from 'react';
import SmallLabelInput from '../common/SmallLabelInput';
import Button from '../common/Button';
// import { useQuery } from '@tanstack/react-query';
// import { getGroupInfo } from '../../api/groupApi';

function GroupInfoSection() {
  // const { data, isPending, isError } = useQuery({
  //   queryKey: ['groupInfo'],
  //   queryFn: getGroupInfo
  // })
  const [groupName, setGroupName] = useState('');
  const [isGroupNameValid] = useState(true);
  const [isGroupNameDuplicated] = useState<boolean>();
  // 닉네임 중복 확인 api
  //   const { refetch } = useQuery({
  //     ['nicknameValidate'],
  //     () => getNicknameValidate(nickname),
  //     enabled: false
  // }
  //   );
  //   const handleValidateGroupName = () => {
  //     refetch().then((res) => {
  //       if (res && res.data) {
  //         console.log('닉네임 중복인가: ', res.data.data);
  //         setIsNicknameDuplicated(res.data.data);
  //       } else {
  //         console.log('응답 객체 또는 데이터가 존재하지 않습니다.');
  //       }
  //     });
  //   };

  const handleValidateGroupName = () => {};

  const changeGroupName = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setGroupName(e.target.value);
  };

  const checkGroupName = () => {};

  return (
    <section className={styles.groupInfoSection}>
      <div className={styles.profileBox}>
        <span>대표사진</span>
        <ProfileImgBox />
      </div>
      <div className={styles.groupNameBox}>
        <SmallLabelInput
          title="그룹명"
          info={'2~20자(영어(대/소), 숫자)'}
          inputName="password"
          inputValue={groupName}
          onChange={changeGroupName}
          inputType="text"
          inputWidth="shortInput"
          onBlur={checkGroupName}
          isInputValid={isGroupNameValid}
          errorMessage="그룹명 형식이 잘못되었습니다."
        />
        <Button
          clickEvent={handleValidateGroupName}
          buttonName={isGroupNameDuplicated == false ? '✓' : '확인'}
          disabledEvent={isGroupNameDuplicated == false}
        />
      </div>
      <div className={styles.rewardBox}>
        <span>먹적</span>
        <div>
          <img src="" alt="" />
          <span>오늘의 제육왕</span>
        </div>
      </div>

      {/* <div className={styles.groupNameBox}>
        <span>그룹명</span>
      </div> */}
    </section>
  );
}

export default GroupInfoSection;
