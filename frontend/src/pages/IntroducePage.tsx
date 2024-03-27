import MbtiIntroduceSection from '../components/common/MbtiIntroduceSection';
import MbtiSection from '../components/common/MbtiSection';
import styles from '../styles/introducePage/IntroducePage.module.css';

function IntroducePage() {
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
      <MbtiSection mbti={mbti}/>
      <MbtiIntroduceSection />
    </div>
  );
}

export default IntroducePage;
