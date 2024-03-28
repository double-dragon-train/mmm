import { useEffect, useState } from 'react';
import Modal from '../components/common/Modal';
import RecordModal from '../components/mainPage/RecordModal';
import styles from '../styles/mainPage/MainPage.module.css';
import MainRecommendSection from '../components/mainPage/MainRecommendSection';

function MainPage() {
  const [isRecordModalOpen, setIsRecordModalOpen] = useState(false);

  const handleCloseModal = () => {
    setIsRecordModalOpen(false);
  };

  const handleCreateRecord = () => {};

  useEffect(() => {
    // setIsRecordModalOpen(true);
    setIsRecordModalOpen(false);
  }, []);

  return (
    <div className={styles.wrapper}>
      <MainRecommendSection />

      {isRecordModalOpen && (
        <Modal clickEvent={handleCloseModal}>
          <RecordModal
            handleCloseModal={handleCloseModal}
            handleCreateRecord={handleCreateRecord}
          />
        </Modal>
      )}
    </div>
  );
}

export default MainPage;
