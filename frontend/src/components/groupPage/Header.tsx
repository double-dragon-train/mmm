import styles from '../../styles/groupPage/GroupPage.module.css';
import log from '../../assets/images/log.png';
import exit from '../../assets/images/exit.png';
import { useState } from 'react';
import LogBox from './LogBox';
import Modal from '../common/Modal';
import ConfirmModal from '../common/ConfirmModal';
import userStore from '../../stores/userStore';
import { useMutation } from '@tanstack/react-query';
import { deleteGroup } from '../../api/groupApi';
import { useNavigate } from 'react-router-dom';

function Header() {
  const { groupId } = userStore();
  const [isLogOpen, setIsLogOpen] = useState(false);
  const [isConfrimModalOpen, setIsConfrimModalOpen] = useState(false);
  const navigate = useNavigate();
  const { mutate: mutateDeleteGroup } = useMutation({
    mutationFn: deleteGroup,
    onSuccess: () => {
      closeConfirmModal()
      navigate('/')
    },
    onError: (error) => {
      console.error('에러발생:', error)
    }
  })
  const toggleIsLogOpen = () => {
    setIsLogOpen(!isLogOpen);
  };

  const handleOpenConfirmModal = () => {
    setIsConfrimModalOpen(true);
  };

  const closeConfirmModal = () => {
    setIsConfrimModalOpen(false);
  };

  const exitGroup = () => {
    mutateDeleteGroup(groupId)
  }
  return (
    <div className={styles.header}>
      {isConfrimModalOpen && (
        <Modal clickEvent={closeConfirmModal}>
          <ConfirmModal
            content="정말로 먹보를 추방하시겠습니까?"
            yesEvent={exitGroup}
            noEvent={closeConfirmModal}
          />
        </Modal>
      )}
      <div className={styles.titleBox}>
        <span>먹그룹</span>
        <img onClick={handleOpenConfirmModal} src={exit} alt="" />
      </div>
      <div className={styles.logBox} onClick={toggleIsLogOpen}>
        <img src={log} alt="" />
        <span>먹로그</span>
      </div>
      {isLogOpen && <LogBox />}
    </div>
  );
}

export default Header;
