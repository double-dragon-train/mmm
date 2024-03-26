import MbtiIntroduceSection from '../components/common/MbtiIntroduceSection';
import MbtiSection from '../components/common/MbtiSection';
import styles from '../styles/introducePage/IntroducePage.module.css';

function IntroducePage() {

  return (
    <div className={styles.wrapper}>
      <MbtiSection mbti=''/>
      <MbtiIntroduceSection />
    </div>
  );
}

export default IntroducePage;
