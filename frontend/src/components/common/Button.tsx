import '../../styles/common/buttons.css';

interface ButtonProps {
  clickEvent: () => void;
  buttonName: string;
}

function Button ({ clickEvent, buttonName }: ButtonProps) {
  return (
    <button onClick={clickEvent} className="miniBlueButton">
      {buttonName}
    </button>
  );
}

export default Button;
