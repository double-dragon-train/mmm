// import { useQuery } from '@tanstack/react-query';
import styles from '../../styles/common/MbtiSection.module.css';
// import { getMbtiResult } from '../../api/mbtiApi';

interface firstSecondType {
  eng: string;
  kor: string;
}
interface mbtiOptionsType {
  id: number;
  first: firstSecondType;
  second: firstSecondType;
  barColor: string;
}
const MBTI_OPTIONS: mbtiOptionsType[] = [
  {
    id: 1,
    first: { eng: 'Ignite', kor: '맵당당' },
    second: { eng: 'Emergency', kor: '맵찌질' },
    barColor: '#FF0000',
  },
  {
    id: 2,
    first: { eng: 'Ssal', kor: '밥파' },
    second: { eng: 'Noodle', kor: '면파' },
    barColor: '#FFB800',
  },
  {
    id: 3,
    first: { eng: 'Fitness', kor: '건강식' },
    second: { eng: 'Total', kor: '일반식' },
    barColor: '#11CF00',
  },
  {
    id: 4,
    first: { eng: 'Papa', kor: '아재입맛' },
    second: { eng: 'Jammine', kor: '잼민입맛' },
    barColor: '#0029FF',
  },
];

function MbtiSection() {
  // const { data, isPending, isError } = useQuery({
  //   queryKey: ['mbtiResult'],
  //   queryFn: getMbtiResult
  // })

  // if (isPending) {
  //   return <div>isLoding...</div>;
  // }
  // if (isError) {
  //   return <div>error</div>;
  // }

  return (
    <section className={styles.mbtiSection}>
      <div className={styles.mbtiTitleBox}>
        <span>먹BTI</span>
        <div>ISTP</div>
      </div>
      {MBTI_OPTIONS.map((mbtiOption) => {
        return (
          <div className={styles.mbtiBarBox} key={mbtiOption.id}>
            <div className={styles.mbtiNameBox}>
              <span>{mbtiOption.first.eng}</span>
              <span>{mbtiOption.first.kor}</span>
            </div>
            <div className={styles.mbtiBarBackGround}>
              <div className={styles.mbtiBar} style={{backgroundColor: mbtiOption.barColor}}>gd</div>
            </div>
            <div className={styles.mbtiNameBox}>
              <span>{mbtiOption.second.eng}</span>
              <span>{mbtiOption.second.kor}</span>
            </div>
          </div>
        );
      })}
    </section>
  );
}

export default MbtiSection;
