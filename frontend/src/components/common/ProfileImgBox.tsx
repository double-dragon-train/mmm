import { useRef, useState } from 'react';
import member from '../../assets/images/member.png';
import styles from '../../styles/common/ProfileImgBox.module.css';

function ProfileImgBox() {
  const inputRef = useRef<HTMLInputElement>(null);
  const [previewImg, setPreviewImg] = useState('');

  const handleClickInput = () => {
    if (inputRef.current !== null) {
      inputRef.current.click();
    }
  };

  const handleUploadImg = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    console.log('event:',e);
    
    // console.log(e.target.files[0].name);
    if (!e.target.value) {
      return;
    }
    setPreviewImg(e.target.value)
  };

  return (
    <div className={styles.profileImgBox} onClick={handleClickInput}>
      <img src={previewImg || member} alt="" />
      <button>+</button>
      <input
        type="file"
        accept=".png, .jpg,image/*"
        style={{ display: 'none' }}
        onChange={handleUploadImg}
        ref={inputRef}
      />
    </div>
  );
}

export default ProfileImgBox;
