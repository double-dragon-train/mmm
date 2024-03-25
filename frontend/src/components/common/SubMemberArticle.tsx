import styles from '../../styles/groupPage/MemberSection.module.css';
import MiniRedButton from './MiniRedButton';
import editMukbot from '../../assets/images/editMukbot.png'

interface SubMemberArticleProps {
  articleName: string;
  memberName: string;
  memberMBTI: string;
  buttonName: string;
}

function SubMemberArticle({
  articleName,
  memberName,
  memberMBTI,
  buttonName,
}: SubMemberArticleProps) {

  return (
    <article>
        <span>{articleName}</span>
        <div className={styles.memberBox}>
          <div className={styles.memberCard}>
            {articleName == '먹봇' ? <img src={editMukbot} alt="editMukbot"/> : <div></div> }
            <div className={styles.memberName}>
              {memberName}
            </div>
            <div>{memberMBTI}</div>
          </div>
          <MiniRedButton clickEvent={() => {}} buttonName={buttonName} />
        </div>
      </article>
  );
}

export default SubMemberArticle;
