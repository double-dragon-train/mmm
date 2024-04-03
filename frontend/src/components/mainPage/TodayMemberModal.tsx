import styles from '../../styles/mainPage/MainPage.module.css';
import memberCnt from '../../assets/images/memberCnt.png';
import userStore from '../../stores/userStore';

function TodayMemberModal() {
  const {
    todayMemberList,
    nextMemberList,
  } = userStore();
  return (
    <div className={styles.todayMemberModalWrapper}>
      <div className={styles.todayMemberHeader}>
        <h2>오늘의 멤버 변경</h2>
        <div>
          <img src={memberCnt} alt="" />
          <span>
            {todayMemberList.length}/
            {todayMemberList.length + nextMemberList.length}
          </span>
        </div>
      </div>
      <section className={styles.todayMemberSection}>
        <h5>오늘 같이 먹어요</h5>
        {todayMemberList.map((member) => {
          console.log('dddddddddddd', member);
          return (
            <article
              key={member.mukboId}
              className={styles.memberBox}
            >
              <div className={styles.memberWhiteBox}>
                <span className={styles.memberName}>
                  {member.name}
                </span>
                <span className={styles.memberMBTI}>
                  {member.mukBTI}
                </span>
              </div>
              {/* <button className={}>-</button> */}
            </article>
          );
        })}
      </section>
      <section className={styles.nextMemberSection}>
        <h5>아쉽지만 다음에...</h5>
        {nextMemberList?.map((member) => {
          console.log('dddddddddddd', member);
          return (
            <article
              key={member.mukboId}
              className={styles.memberBox}
            >
              <div className={styles.memberWhiteBox}>
                <span className={styles.memberName}>
                  {member.name}
                </span>
                <span className={styles.memberMBTI}>
                  {member.mukBTI}
                </span>
              </div>
              {/* <button className={}>-</button> */}
            </article>
          );
        })}
      </section>
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
