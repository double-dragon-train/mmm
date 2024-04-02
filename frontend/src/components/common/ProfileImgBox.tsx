import { useState } from 'react';
import member from '../../assets/images/member.png';
import styles from '../../styles/common/ProfileImgBox.module.css';

function ProfileImgBox() {
  const [previewImg, setPreviewImg] = useState('');

  const handleUploadImg = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    const files = e.target.files;
    const uploadedImages: any = [];

    console.log('event:', files);
    const uploadFile = files![0];
    const reader = new FileReader();
    reader.readAsDataURL(uploadFile);
    reader.onloadend = () => {
      uploadedImages.push(reader.result);
      setPreviewImg(uploadedImages);
    };
    
  };

  return (
    <label htmlFor="profileImg" className="">
      <div className={styles.profileImgBox}>
        <img src={previewImg || member} className={previewImg ? styles.profileImg : styles.defaultImg} alt="" />
        <button>+</button>
        <input
          id="profileImg"
          type="file"
          accept="image/*"
          style={{ display: 'none' }}
          onChange={handleUploadImg}
        />
      </div>
    </label>
  );
}

export default ProfileImgBox;
