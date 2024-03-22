import member from '../../assets/images/member.png';
import styles from '../../styles/common/ProfileImgBox.module.css';

function ProfileImgBox() {
  return (
    <div className={styles.profileImgBox}>
      <img src={member} alt="" />
      <button>+</button>
    </div>
  );
}

export default ProfileImgBox;
