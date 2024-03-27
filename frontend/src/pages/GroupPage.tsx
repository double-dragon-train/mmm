import GroupInfoSection from '../components/groupPage/GroupInfoSection';
import Header from '../components/groupPage/Header';
import MbtiSection from '../components/common/MbtiSection';
import MemberSection from '../components/groupPage/MemberSection';
import styles from '../styles/groupPage/GroupPage.module.css';

function GroupPage() {
  const mbti = {
    'ei': 1,
    'ns': 2,
    'tf': 3,
    'jp': 4,
    'mint': 4,
    'pine': 4,
    'die': 4,
  };
  return (
    <div className={styles.wrapper}>
      <Header />
      <GroupInfoSection />
      <MbtiSection mbti={mbti} />
      <MemberSection />
    </div>
  );
}

export default GroupPage;
