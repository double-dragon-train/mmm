import { useEffect, useState } from 'react';
import Modal from '../components/common/Modal';
import RecordModal from '../components/mainPage/RecordModal';
import styles from '../styles/mainPage/MainPage.module.css';
import MainRecommendSection from '../components/mainPage/MainRecommendSection';
import { useQuery } from '@tanstack/react-query';
import { getGroupInfo } from '../api/groupApi';
import WeatherRecommendSection from '../components/mainPage/WeatherRecommendSection';
import NewRecommendSection from '../components/mainPage/NewRecommendSection';
import GroupSection from '../components/mainPage/GroupSection';

function MainPage() {
  const [isRecordModalOpen, setIsRecordModalOpen] = useState(false);
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const {
    data: groupInfo,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['groupInfo'],
    queryFn: getGroupInfo,
  });

  const toggleIsCreateModalOpen = () => {
    setIsCreateModalOpen(!isCreateModalOpen);
  };
  const handleCloseModal = () => {
    setIsRecordModalOpen(false);
    setIsCreateModalOpen(false);
  };

  const handleCreateRecord = () => {};

  useEffect(() => {
    // setIsRecordModalOpen(true);
    setIsRecordModalOpen(false);
  }, []);

  if (isPending) {
    return <div>isLoding...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }
  return (
    <div className={styles.wrapper}>
      <MainRecommendSection groupId={groupInfo.mukgroupId} />
      <WeatherRecommendSection />
      <NewRecommendSection />
      <GroupSection
        toggleIsCreateModalOpen={toggleIsCreateModalOpen}
      />

      {isRecordModalOpen && (
        <Modal clickEvent={handleCloseModal}>
          <RecordModal
            handleCloseModal={handleCloseModal}
            handleCreateRecord={handleCreateRecord}
          />
        </Modal>
      )}

      {isCreateModalOpen && (
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
