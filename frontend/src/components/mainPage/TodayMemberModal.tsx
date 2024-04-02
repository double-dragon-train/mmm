import styles from '../../styles/mainPage/MainPage.module.css';
import memberCnt from '../../assets/images/memberCnt.png';
// import SubMemberArticle from '../common/SubMemberArticle';
// import SubMemberCard from '../common/SubMemberCard';

function TodayMemberModal() {
  return (
    <div className={styles.todayMemberModalWrapper}>
      <div className={styles.todayMemberHeader}>
        <h2>오늘의 멤버 변경</h2>
        <div>
          <img src={memberCnt} alt="" />
          <span>agdgd</span>
        </div>
      </div>
      {/* <SubMemberArticle
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
      </SubMemberArticle> */}
    </div>
  );
}

export default TodayMemberModal;
