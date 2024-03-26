// import MbtiSection from '../components/common/MbtiSection';
// import styles from '../styles/introducePage/IntroducePage.module.css';
import ignite from '../assets/images/ignite.png';
import emergency from '../assets/images/emergency.png';
import ssal from '../assets/images/ssal.png';
import noodle from '../assets/images/noodle.png';
import fitness from '../assets/images/fitness.png';
import total from '../assets/images/total.png';
import papa from '../assets/images/papa.png';
import jammin from '../assets/images/jammin.png';
import IntroducePage from './IntroducePage';

interface firstSecondType {
  eng: string;
  kor: string;
  img: string;
}

interface mbtiIntroduceOptionsType {
  id: number;
  title: string;
  first: firstSecondType;
  second: firstSecondType;
}

const MBTI_INTRODUCE_OPTIONS: mbtiIntroduceOptionsType[] = [
  {
    id: 1,
    title: '매운맛 척도',
    first: {
      eng: 'Ignite',
      kor: '어느 맛이든\n두렵지 않은 맵당당이',
      img: ignite,
    },
    second: {
      eng: 'Emergency',
      kor: '눈물 쏙! 콧물 쏙!\n맵찔이',
      img: emergency,
    },
  },
  {
    id: 2,
    title: '선호 탄수화물',
    first: {
      eng: 'Ssal',
      kor: '한국인은 밥심!\n밥파',
      img: ssal,
    },
    second: {
      eng: 'Noodle',
      kor: '면치기의 나라!\n면파',
      img: noodle,
    },
  },
  {
    id: 3,
    title: '건강 중요도',
    first: {
      eng: 'Fitness',
      kor: '맛보다는 건강!\n건강식파',
      img: fitness,
    },
    second: {
      eng: 'Total',
      kor: '먹을 때 제일 행복해!\n일반식파!',
      img: total,
    },
  },
  {
    id: 4,
    title: '입맛의 성숙함',
    first: {
      eng: 'Papa',
      kor: '점심은 뜨끈~한 국밥!\n아재입맛',
      img: papa,
    },
    second: {
      eng: 'Jammin',
      kor: '엄망 소세지 없엉?\잼민입맛',
      img: jammin,
    },
  },
];
console.log(MBTI_INTRODUCE_OPTIONS)

function MbtiResultPage() {
  return (
    <IntroducePage />
  )
}

export default MbtiResultPage