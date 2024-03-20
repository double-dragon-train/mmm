import { Link } from 'react-router-dom';
import styles from '../styles/landingPage/LandingPage.module.css';
import '../styles/common/buttons.css';
import mainLogo from '../assets/images/mainLogo.png';
import { getRandomFoodList } from '../api/recommendApi';
import { useQuery } from 'react-query';
import { useEffect, useState } from 'react';

function LandingPage() {
  const { data, isLoading, isError } = useQuery(
    ['randomFoodList'],
    getRandomFoodList
  );
  const [currentImageIndex, setCurrentImageIndex] =
    useState<number>(0);
  const [isHovered, setIsHovered] = useState<boolean>(false);

  useEffect(() => {
    const interval = setInterval(() => {
      if (!isHovered) {
        setCurrentImageIndex(
          (prevIndex) => (prevIndex + 1) % data.foods.length
        );
      }
    }, 150);

    return () => clearInterval(interval);
  }, [isHovered, data]);

  const handleMouseEnter = () => {
    setIsHovered(true);
  };

  const handleMouseLeave = () => {
    setIsHovered(false);
  };

  if (isLoading) {
    return <div>isLoding...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  return (
    <div className={styles.wrapper}>
      <img className={styles.mainLogo} src={mainLogo} alt="" />
      <div
        className={styles.randomFoodBox}
        onMouseOver={handleMouseEnter}
        onMouseLeave={handleMouseLeave}
      >
        <img
          className={styles.randomFood}
          src={data.foods[currentImageIndex].imageSrc}
          alt=""
        />
        <span>{data.foods[currentImageIndex].name}</span>
      </div>

      <div>
        <Link to="/login" className="landingButton">
          로그인
        </Link>
        <Link to="/mbti/1" className="landingButton">
          먹BTI 검사
        </Link>
      </div>
    </div>
  );
}

export default LandingPage;
