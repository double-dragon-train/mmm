import styles from '../../styles/groupPage/MemberSection.module.css';
import SubMemberArticle from '../common/SubMemberArticle';

function MemberSection() {
  return (
    <section className={styles.memberSection}>
      <span>구성원</span>
      <SubMemberArticle
        articleName="먹보"
        memberName="개꼰대까다로운한팀장"
        memberMBTI="ISFP"
        buttonName="추방"
      />
      <SubMemberArticle
        articleName="먹봇"
        memberName="까다로운한팀장"
        memberMBTI="ISFP"
        buttonName="삭제"
      />
    </section>
  );
}

export default MemberSection;
