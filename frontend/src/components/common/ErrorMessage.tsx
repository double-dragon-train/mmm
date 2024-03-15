import '../../styles/common/errorMessage.css';

interface ErrorMessageProps {
  errorFontSize: string;
  errorTarget: string;
}

function ErrorMessage({
  errorFontSize,
  errorTarget,
}: ErrorMessageProps) {
  return (
    <div className={errorFontSize}>
      [{errorTarget}] 형식이 올바르지 않습니다.
    </div>
  );
}

export default ErrorMessage;
