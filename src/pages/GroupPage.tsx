import GroupInfoSection from '../components/groupPage/GroupInfoSection';
import Header from '../components/groupPage/Header';
import MbtiSection from '../components/common/MbtiSection';
import MemberSection from '../components/groupPage/MemberSection';
import styles from '../styles/groupPage/GroupPage.module.css';

function GroupPage() {
  return (
    <div className={styles.wrapper}>
      <Header />
      <GroupInfoSection />
      <MbtiSection />
      <MemberSection />
    </div>
  );
}

export default GroupPage;
