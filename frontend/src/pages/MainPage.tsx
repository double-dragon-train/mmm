import { useEffect, useState } from 'react';
import Modal from '../components/common/Modal';
import RecordModal from '../components/mainPage/RecordModal';
import styles from '../styles/mainPage/MainPage.module.css';

function MainPage() {
  const [isRecordModalOpen, setIsRecordModalOpen] = useState(false);

  const handleCloseModal = () => {
    setIsRecordModalOpen(false);
  };

  const handleCreateRecord = () => {};

  useEffect(() => {
    setIsRecordModalOpen(true);
  }, []);
  return (
    <div className={styles.wrapper}>
      <section className={styles.recommendSection}>
        <h2></h2>
      </section>

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
