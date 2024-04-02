import { useEffect, useRef, useState } from 'react';
import member from '../../assets/images/member.png';
import styles from '../../styles/common/ProfileImgBox.module.css';
import { modifyGroupImage } from '../../api/groupApi.ts';

interface ProfileImgBoxProps{
  groupId : number,
  imageSrc : string | null
}
function ProfileImgBox({groupId, imageSrc}:ProfileImgBoxProps) {
  const [previewImg, setPreviewImg] = useState('');
  const ref =  useRef<HTMLInputElement>(null);

  useEffect(() => {
    if(imageSrc === null)
      setPreviewImg('')
    else
      setPreviewImg(imageSrc)
  }, [imageSrc]);

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


    modifyGroupImage({groupId,
                groupImg : uploadFile});
  };

  const handleImageAddButtonClicked = () => {
    if(ref.current)
      ref.current.click();
  }

  return (
      <div className={styles.profileImgBox}>
        <img src={previewImg || member} className={previewImg ? styles.profileImg : styles.defaultImg} alt="" />
        <button onClick={handleImageAddButtonClicked}>+</button>
        <input
          ref={ref}
          id="profileImg"
          type="file"
          accept="image/*"
          style={{ display: 'none' }}
          onChange={handleUploadImg}
        />
      </div>
  );
}

export default ProfileImgBox;
