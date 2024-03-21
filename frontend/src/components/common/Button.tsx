import '../../styles/common/buttons.css';

interface ButtonProps {
  clickEvent: () => void;
  disabledEvent: boolean;
  buttonName: string;
}

function Button ({ clickEvent, buttonName, disabledEvent }: ButtonProps) {
  return (
    <button onClick={clickEvent} disabled={disabledEvent} className="miniBlueButton">
      {buttonName}
    </button>
  );
}

export default Button;
