// import { useQuery } from 'react-query';
import { useQuery } from '@tanstack/react-query';
import { getMbtiQuestionList } from '../api/mbti';
import { Link, useParams } from 'react-router-dom';
import styles from '../styles/mbtiPage/MbtiPage.module.css';
// import styles from '../pages/MbtiPage.module.css';
import '../styles/common/buttons.css';
import CheckCircle from '../components/mbtiPage/CheckCircle';

interface answerType {
  answerId: string;
  answerContext: string;
  answerImage: string;
}

function MbtiPage() {
  const { mbtiId } = useParams();
  const { data, isPending, isError } = useQuery({
    queryKey: ['mbtiQuestionList'],
    queryFn: getMbtiQuestionList,
  });

  const handleSelectAnswer = () => {};
  if (isPending) {
    return <div>isLoding...</div>;
  }

  if (isError) {
    return <div>error</div>;
  }
  console.log(data);

  if (data[Number(mbtiId)].answers.length === 5)
    return (
      <div className={styles.wrapper}>
        {/* <img className={styles.mainLogo} src={mainLogo} alt="" /> */}
        <h1>{data[Number(mbtiId)].context}</h1>
        <section>
          <span>
            {Number(mbtiId) + 1} / {data.length}
          </span>
          {data[Number(mbtiId)].answers.map((answer: answerType) => {
            return (
              <div key={answer.answerId} onClick={handleSelectAnswer}>
                <CheckCircle />
                <span>{answer.answerContext}</span>
                <div className={styles.imageBox}>
                  <img src={answer.answerImage} alt="" />
                </div>
              </div>
            );
          })}
          {/* <CheckCircle /> */}
        </section>
        <div className={styles.randomFoodBox}>
          <img className={styles.randomFood} alt="" />
        </div>
        <Link
          to={`/mbti/${Number(mbtiId!) + 1}`}
          className="miniRoundedButton"
        >
          다음
        </Link>
      </div>
    );

  if (data[Number(mbtiId)].answers.length === 2)
    return (
      <div className={styles.wrapper2}>
        <h1>{data[Number(mbtiId)].context}</h1>
        <section>
          <span>
            {Number(mbtiId) + 1} / {data.length}
          </span>

          <div
            className={styles.answerBox}
            onClick={handleSelectAnswer}
          >
            <span>
              {data[Number(mbtiId)].answers[0].answerContext}
            </span>

            <div>
              <img
                src={data[Number(mbtiId)].answers[0].answerImage}
                alt=""
              />
            </div>
          </div>

          <div className={styles.vsText}>VS</div>

          <div
            className={styles.answerBox}
            onClick={handleSelectAnswer}
          >
            <span>
              {data[Number(mbtiId)].answers[0].answerContext}
            </span>
            <div>
              <img
                src={data[Number(mbtiId)].answers[0].answerImage}
                alt=""
              />
            </div>
          </div>
          {/* {data[Number(mbtiId)].answers.map((answer: answerType) => {
            return (
              <div className={styles.answerBox} key={answer.answerId} onClick={handleSelectAnswer}>
                <span>{answer.answerContext}</span>

                <div><img src={answer.answerImage} alt="" /></div>
              </div>
            );
          })} */}
        </section>
        <div className={styles.randomFoodBox}>
          <img className={styles.randomFood} alt="" />
        </div>
        <Link
          to={`/mbti/${Number(mbtiId!) + 1}`}
          className="miniRoundedButton"
        >
          다음
        </Link>
      </div>
    );
}

export default MbtiPage;
