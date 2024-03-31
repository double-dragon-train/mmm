import styles from '../../styles/groupPage/GroupPage.module.css';
import lock from '../../assets/images/lock.png'
import secret from '../../assets/images/secret.png'

interface propsType {
  id: number;
  condition: string;
  name: string;
  isCleared: boolean;
  imageSrc: string | undefined;
}

function RewardCard({
  id,
  condition,
  name,
  isCleared,
  imageSrc,
}: propsType) {
  console.log(id)
  return (
    <article className={styles.rewardItem}>
      <div className={isCleared ? styles.myRewardImgBox : styles.notMyRewardImgBox}>
        <img src={imageSrc} alt="" />
        {!isCleared && <img className={styles.notMyRewardIcon} src={condition === '비밀~' ? secret : lock}/>}
      </div>
      <span className={styles.rewardName}>{name}</span>
      <p>{condition}</p>
    </article>
  );
}

export default RewardCard;
