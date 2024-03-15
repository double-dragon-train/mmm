import { Link } from 'react-router-dom';
import styles from '../styles/landingPage/LandingPage.module.css';
import '../styles/common/buttons.css';
import mainLogo from '../assets/images/mainLogo.png';
import dakbal from '../assets/images/dakbal.jpg';
// import { useQuery } from 'react-query';

function LandingPage() {
  // const { isLoading, isError, data, error } = useQuery("random", fetchTodoList, {
  //   refetchOnWindowFocus: false, // react-query는 사용자가 사용하는 윈도우가 다른 곳을 갔다가 다시 화면으로 돌아오면 이 함수를 재실행합니다. 그 재실행 여부 옵션 입니다.
  //   retry: 0, // 실패시 재호출 몇번 할지
  //   onSuccess: data => {
  //     // 성공시 호출
  //     console.log(data);
  //   },
  //   onError: e => {
  //     // 실패시 호출 (401, 404 같은 error가 아니라 정말 api 호출이 실패한 경우만 호출됩니다.)
  //     // 강제로 에러 발생시키려면 api단에서 throw Error 날립니다. (참조: https://react-query.tanstack.com/guides/query-functions#usage-with-fetch-and-other-clients-that-do-not-throw-by-default)
  //     console.log(e.message);
  //   }
  // });

  // if (isLoading) {
  //   return <span>Loading...</span>;
  // }

  // if (isError) {
  //   return <span>Error: {error.message}</span>;
  // }

  return (
    <div className={styles.wrapper}>
      <img className={styles.mainLogo} src={mainLogo} alt="" />
      <img className={styles.randomFood} src={dakbal} alt="" />
      <div>
        <Link to="/login">
          <button className="landingButton">로그인</button>
        </Link>
        <Link to="/mbti/1">
          <button className="landingButton">먹BTI 검사</button>
        </Link>
      </div>
    </div>
  );
}

export default LandingPage;
