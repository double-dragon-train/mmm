import { useMutation, useQuery } from '@tanstack/react-query';
import { getMbtiQuestionList, getMbtiResult } from '../api/mbtiApi';
import { useNavigate, useParams } from 'react-router-dom';
import styles from '../styles/mbtiPage/MbtiPage.module.css';
import buttonStyles from '../styles/common/Buttons.module.css';
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
  const { updateAnswerList, answerList } = userStore();
  const { mbtiId } = useParams();
  const { mutate: mutateMbtiResult } = useMutation({
    mutationFn: getMbtiResult,
    onSuccess: () => navigate('/introduce'),
  });
  console.log('answerList:', answerList);
  const { data, isPending, isError } = useQuery({
    queryKey: ['mbtiQuestionList'],
    queryFn: getMbtiQuestionList,
  });
  console.log('data:', data);
  useEffect(() => {
    setSelectedAnswer('');
  }, [mbtiId]);

  const handleSelectAnswer = (answerId: string) => {
    setSelectedAnswer(answerId);
  };

  const goNextQuestion = () => {
    if (!selectedAnswer) return;

    const targetQuizId = data[Number(mbtiId)].quizId;

    const isExist = answerList.some((answer) => answer.quizId === targetQuizId)
    
    if (isExist) {
      const updatedList = answerList.map((answer) => {
        if (answer.quizId === targetQuizId) {
          return {...answer, answerId: selectedAnswer}
        }
        return answer;
      })
      updateAnswerList(updatedList);
    } else {
    updateAnswerList([
        ...answerList,
        {
          quizId: data[Number(mbtiId)].quizId,
          answerId: selectedAnswer,
        },
      ]);
    }

    if (mbtiId === '14') {
      mutateMbtiResult(answerList);
    } else {
      navigate(`/mbti/${Number(mbtiId) + 1}`);
    }
  };

  if (isPending) {
    return <div>isLoding...</div>;
  }

  if (isError) {
    return <div>error</div>;
  }

  if (Number(mbtiId) === 8)
    return (
      <div className={styles.wrapper3}>
        <h1>{data[Number(mbtiId)].context}</h1>
        <div className={styles.questionNumBox}>
          {Number(mbtiId) + 1} / {data.length}
        </div>
        <img src={data[Number(mbtiId)].img} alt="" />
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
              </div>
            );
          })}
        </section>
        <div className={styles.randomFoodBox}>
          <img className={styles.randomFood} alt="" />
        </div>
        <button
          onClick={goNextQuestion}
          className={buttonStyles.miniRoundedButton}
        >
          다음
        </button>
      </div>
    );

  if (data[Number(mbtiId)].answers.length === 5)
    return (
      <div className={styles.wrapper}>
        <h1>{data[Number(mbtiId)].context}</h1>
        <div className={styles.questionNumBox}>
          {Number(mbtiId) + 1} / {data.length}
        </div>
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
          className={buttonStyles.miniRoundedButton}
        >
          다음
        </button>
      </div>
    );

  if (data[Number(mbtiId)].answers.length === 2)
    return (
      <div className={styles.wrapper2}>
        <h1>{data[Number(mbtiId)].context}</h1>
        <div className={styles.questionNumBox}>
          {Number(mbtiId) + 1} / {data.length}
        </div>
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
          className={buttonStyles.miniRoundedButton}
        >
          다음
        </button>
      </div>
    );
}

export default MbtiPage;
