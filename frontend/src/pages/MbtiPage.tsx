import { useQuery } from '@tanstack/react-query';
import { getMbtiQuestionList } from '../api/mbti';
import { useNavigate, useParams } from 'react-router-dom';
import styles from '../styles/mbtiPage/MbtiPage.module.css';
import '../styles/common/buttons.css';
import CheckCircle from '../components/mbtiPage/CheckCircle';
import userStore from '../stores/userStore';
import { useEffect, useState } from 'react';
import AnswerBox from '../components/mbtiPage/AnswerBox';

interface answerType {
  answerId: string;
  answerContext: string;
  answerImage: string;
}

function MbtiPage() {
  const navigate = useNavigate();
  const [selectedAnswer, setSelectedAnswer] = useState('');
  const { addAnswerList, answerList, accessToken } = userStore();
  const { mbtiId } = useParams();
  console.log(mbtiId);
  const { data, isPending, isError } = useQuery({
    queryKey: ['mbtiQuestionList'],
    queryFn: getMbtiQuestionList,
  });
  console.log('gdgdgdgd', data);
  useEffect(() => {
    setSelectedAnswer('');
  }, [mbtiId]);

  const handleSelectAnswer = (answerId: string) => {
    setSelectedAnswer(answerId);
  };

  const goNextQuestion = () => {
    if (!selectedAnswer) return;

    if (mbtiId === '14') {
      if (accessToken) {
        navigate('/main');
      } else {
        navigate('/signup');
      }
      return;
    }
    addAnswerList([
      ...answerList,
      {
        quizId: data[Number(mbtiId)].quizId,
        answerId: selectedAnswer,
      },
    ]);
    navigate(`/mbti/${Number(mbtiId) + 1}`);
  };

  if (isPending) {
    return <div>isLoding...</div>;
  }

  if (isError) {
    return <div>error</div>;
  }

  if (data[Number(mbtiId)].answers.length === 5)
    return (
      <div className={styles.wrapper}>
        <h1>{data[Number(mbtiId)].context}</h1>
        <span>
          {Number(mbtiId) + 1} / {data.length}
        </span>
        <section>
          {data[Number(mbtiId)].answers.map((answer: answerType) => {
            return (
              <div key={answer.answerId}>
                <CheckCircle
                  handleSelectAnswer={() =>
                    handleSelectAnswer(answer.answerId)
                  }
                  isSelected={selectedAnswer == answer.answerId}
                />
                <span>{answer.answerContext}</span>
                <div className={styles.imageBox}>
                  <img src={answer.answerImage} alt="" />
                </div>
              </div>
            );
          })}
        </section>
        <div className={styles.randomFoodBox}>
          <img className={styles.randomFood} alt="" />
        </div>
        <button
          onClick={goNextQuestion}
          className="miniRoundedButton"
        >
          다음
        </button>
      </div>
    );

  if (data[Number(mbtiId)].answers.length === 2)
    return (
      <div className={styles.wrapper2}>
        <h1>{data[Number(mbtiId)].context}</h1>
        <span>
          {Number(mbtiId) + 1} / {data.length}
        </span>
        <section>
          <AnswerBox
            handleSelectAnswer={() =>
              handleSelectAnswer(
                data[Number(mbtiId)].answers[0].answerId
              )
            }
            isSelected={
              selectedAnswer ==
              data[Number(mbtiId)].answers[0].answerId
            }
            text={data[Number(mbtiId)].answers[0].answerContext}
            imgSrc={data[Number(mbtiId)].answers[0].answerImage}
          />
          <div className={styles.vsText}>VS</div>
          <AnswerBox
            handleSelectAnswer={() =>
              handleSelectAnswer(
                data[Number(mbtiId)].answers[1].answerId
              )
            }
            isSelected={
              selectedAnswer ==
              data[Number(mbtiId)].answers[1].answerId
            }
            text={data[Number(mbtiId)].answers[1].answerContext}
            imgSrc={data[Number(mbtiId)].answers[1].answerImage}
          />
        </section>
        <div className={styles.randomFoodBox}>
          <img className={styles.randomFood} alt="" />
        </div>
        <button
          onClick={goNextQuestion}
          className="miniRoundedButton"
        >
          다음
        </button>
      </div>
    );
}

export default MbtiPage;
