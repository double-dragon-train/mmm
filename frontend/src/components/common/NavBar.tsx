import { Link } from 'react-router-dom';
import calendar_white from '../../assets/icons/calendar_white.svg'

function NavBar() {
  return (
    <nav>
      <Link to="/calendar">
        <img src={calendar_white} alt="" />
        <span>먹어쓰</span>
      </Link>
    </nav>
  );
}

export default NavBar;
