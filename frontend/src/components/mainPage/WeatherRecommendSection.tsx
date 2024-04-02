import styles from '../../styles/mainPage/MainPage.module.css';
import jammin from '../../assets/images/jammin.png';
import { useQuery } from '@tanstack/react-query';
import { getWeatherRecommendFood } from '../../api/recommendApi';
import { useEffect, useState } from 'react';

function WeatherRecommendSection() {
  const [latitude, setLatitude] = useState<number | null>(null);
  const [longitude, setLongitude] = useState<number | null>(null);

  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function (position) {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;

        setLatitude(latitude);
        setLongitude(longitude);
      });
    }
  }, []);

  const {
    data: weatherRecommendFood,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['weatherRecommendFood'],
    queryFn: () =>
      getWeatherRecommendFood(String(latitude), String(longitude)),
    enabled: latitude !== null && longitude !== null,
  });

  console.log('날씨:', weatherRecommendFood);
  if (isPending) {
    return <div>Loading...</div>;
  }
  if (isError) {
    return null;
  }

  return (
    <section className={styles.weatherRecommendSection}>
      <div className={styles.textBox}>
        <h2>날씨별 추천</h2>
        <div>
          비도 오는데
          <br />
          혹시 <span className={styles.foodName}>
            [ 마라탕 ]
          </span>{' '}
          어때요?
        </div>
      </div>
      <img src={jammin} alt="" />
    </section>
  );
}

export default WeatherRecommendSection;
