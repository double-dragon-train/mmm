import '../../styles/common/errorMessage.css';

interface ErrorMessageProps {
  errorMessage: string;
}

function ErrorMessage({ errorMessage }: ErrorMessageProps) {
  return (
    <div className="errorMessage">
      {errorMessage}
    </div>
  );
}

export default ErrorMessage;
