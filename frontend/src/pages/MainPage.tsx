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
import { getRecentRecommendFood } from '../api/recommendApi';

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

  console.log('gdgdgdddddddddd:', groupInfo);
  const { data: recentFoodList } = useQuery({
    queryKey: ['recentRecommendFood'],
    queryFn: () => getRecentRecommendFood(groupInfo.groupId),
    enabled: groupInfo !== undefined,
  });
  console.log(recentFoodList)
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
    setGroupId(groupInfo?.mukgroupId);
  }, [groupInfo]);

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
      <NewRecommendSection groupId={groupInfo.mukgroupId} />
      <GroupSection
        hadleOpenCreateModal={hadleOpenCreateModal}
        isSolo={groupInfo.isSolo}
        groupId={groupInfo.mukgroupId}
        groupName={groupInfo.name}
      />

      {isRecordModalOpen && (
        <Modal clickEvent={handleCloseRecordModal}>
          <RecordModal
            handleCloseModal={handleCloseRecordModal}
            handleCreateRecord={handleCreateRecord}
            groupId={groupInfo.mukgroupId}
          />
        </Modal>
      )}

      {isCreateModalOpen && (
        <Modal clickEvent={handleCloseCreateModal}>
          <CreateGroupModal />
        </Modal>
      )}
    </div>
  );
}

export default MainPage;
