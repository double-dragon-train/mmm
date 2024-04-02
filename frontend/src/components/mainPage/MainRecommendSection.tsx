import styles from '../../styles/mainPage/MainPage.module.css';
import reroll from '../../assets/images/reroll.png';
import { useQuery } from '@tanstack/react-query';
import { getMainRecommendFood } from '../../api/recommendApi';
import alert from '../../assets/images/alert.png';
import userStore from '../../stores/userStore';
import { useEffect, useState } from 'react';
import Roullete from './Roullete';
import Modal from '../common/Modal';
import SelectedFoodModal from './SelectedFoodModal';

interface propsType {
  groupId: number;
}
function MainRecommendSection({ groupId }: propsType) {
  const { mbti } = userStore();
  const [selectedFoodId, setSelectedFoodId] = useState<number | null>(
    null
  );
  const [selectedFoodName, setSelectedFoodName] =
    useState<string>('');
  const [selectedFoodImg, setSelectedFoodImg] = useState<string>('');
  const [isRouletteOpen, setIsRouletteOpen] =
    useState<boolean>(false);
  const [isSelectedFoodModalOpen, setIsSelectedFoodModalOpen] =
    useState<boolean>(false);
  const {
    data: mainRecommendFoodList,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['main'],
    queryFn: () => getMainRecommendFood(groupId, mbti),
  });

  const closeSelectedFoodModal = () => {
    setIsSelectedFoodModalOpen(false);
  };

  const openSelectedFoodModal = () => {
    setIsSelectedFoodModalOpen(true);
  };

  const toggleIsRouletteOpen = () => {
    setIsRouletteOpen(!isRouletteOpen);
  };

  useEffect(() => {
    if (selectedFoodId !== null) {
      setSelectedFoodName(mainRecommendFoodList[selectedFoodId].name);
      setSelectedFoodImg(mainRecommendFoodList[selectedFoodId].image);
    }
  }, [selectedFoodId]);
  console.log(mainRecommendFoodList);

  if (isPending) {
    return <div>isLoding...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  return (
    <section className={styles.mainRecommendSection}>
      <header className={styles.header}>
        <div className={styles.titleBox}>
          <h2>추천메뉴</h2>
          <img src={alert} alt="" />
        </div>
        <img src={reroll} alt="" onClick={toggleIsRouletteOpen} />
      </header>
      <main>
        {isRouletteOpen ? (
          <Roullete
            mainRecommendFoodList={mainRecommendFoodList}
            openSelectedFoodModal={openSelectedFoodModal}
            setSelectedFoodId={setSelectedFoodId}
          />
        ) : (
          <div className={styles.recommendFoodBox}>
            <img src={mainRecommendFoodList[0].image} alt="" />
            <span>{mainRecommendFoodList[0].name}</span>
          </div>
        )}
      </main>
      {isSelectedFoodModalOpen && (
        <Modal clickEvent={closeSelectedFoodModal}>
          <SelectedFoodModal
            selectedFoodName={selectedFoodName}
            selectedFoodImg={selectedFoodImg}
          />
        </Modal>
      )}
    </section>
  );
}

export default MainRecommendSection;
