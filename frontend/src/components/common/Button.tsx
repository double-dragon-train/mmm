import '../../styles/common/buttons.css';

function Button({ clickEvent, buttonName }) {
  return (
    <button onClick={clickEvent} className="miniBlueButton">
      {buttonName}
    </button>
  );
}

export default Button;
