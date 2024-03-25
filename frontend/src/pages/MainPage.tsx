import { useEffect, useState } from 'react';
import Modal from '../components/common/Modal';
import RecordModal from '../components/mainPage/RecordModal';

function MainPage() {
  const [isRecordModalOpen, setIsRecordModalOpen] = useState(false);

  const handleCloseModal = () => {
    setIsRecordModalOpen(false);
  };

  const handleCreateRecord = () => {
    
  }

  useEffect(() => {
    setIsRecordModalOpen(true);
  }, []);
  return (
    <div>
      <div>MainPage</div>

      {isRecordModalOpen && (
        <Modal clickEvent={handleCloseModal}>
          <RecordModal handleCloseModal={handleCloseModal} handleCreateRecord={handleCreateRecord}/>
        </Modal>
      )}
    </div>
  );
}

export default MainPage;
