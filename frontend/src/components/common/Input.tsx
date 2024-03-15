import '../../styles/common/Input.css';

function Input({ title, info, inputWidth }) {
  return (
    <div className="inputContainer">
      <label>{title}</label>
      <div className="inputBox">
        <input type="text" className={inputWidth} />
        <span>{info}</span>
      </div>
    </div>
  );
}

export default Input;
