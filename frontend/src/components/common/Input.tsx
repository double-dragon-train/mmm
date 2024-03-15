import '../../styles/common/Input.css';

interface InputProps { 
  title: string,
  info: string,
  inputWidth: string,
}

function Input({ title, info, inputWidth }: InputProps) {
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
