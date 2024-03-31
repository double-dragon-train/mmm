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
import CreateGroupModal from '../components/mainPage/CreateGroupModal';
import userStore from '../stores/userStore';

function MainPage() {
  const { setGroupId } = userStore();
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

  const hadleOpenCreateModal = () => {
    setIsCreateModalOpen(true);
  };
  const handleCloseCreateModal = () => {
    setIsCreateModalOpen(false);
  };
  const handleCloseRecordModal = () => {
    setIsRecordModalOpen(false);
  };
 

  const handleCreateRecord = () => {};

  useEffect(() => {
    setGroupId(groupInfo?.mukgroupId)
  }, [groupInfo])
  useEffect(() => {
    // setIsRecordModalOpen(true);
    // setIsRecordModalOpen(true);
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
        hadleOpenCreateModal={hadleOpenCreateModal}
        isSolo={groupInfo.isSolo}
        groupId={groupInfo.mukgroupId}
      />

      {isRecordModalOpen && (
        <Modal clickEvent={handleCloseRecordModal}>
          <RecordModal
            handleCloseModal={handleCloseRecordModal}
            handleCreateRecord={handleCreateRecord}
          />
        </Modal>
      )}

      {isCreateModalOpen && (
        <Modal clickEvent={handleCloseCreateModal}>
          <CreateGroupModal
          />
        </Modal>
      )}
    </div>
  );
}

export default MainPage;
