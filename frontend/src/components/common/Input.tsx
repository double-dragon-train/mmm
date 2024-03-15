import '../../styles/common/Input.css';

interface InputProps { 
  title: string,
  info: string,
  inputType: string,
  inputWidth: string,
}

function Input({ title, info, inputType, inputWidth }: InputProps) {
  return (
    <div className="inputContainer">
      <label>{title}</label>
      <div className="inputBox">
        <input type={inputType} className={inputWidth} />
        <span>{info}</span>
      </div>
    </div>
  );
}

export default Input;
