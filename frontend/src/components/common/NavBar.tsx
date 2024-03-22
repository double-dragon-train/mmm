import { NavLink, useLocation } from 'react-router-dom';
import calendar_white from '../../assets/images/calendar_white.png';
import calendar_blue from '../../assets/images/calendar_blue.png';
import group_white from '../../assets/images/group_white.png';
import group_blue from '../../assets/images/group_blue.png';
import home_white from '../../assets/images/home_white.png';
import home_blue from '../../assets/images/home_blue.png';
import mbti_white from '../../assets/images/mbti_white.png';
import mbti_blue from '../../assets/images/mbti_blue.png';
import profile_white from '../../assets/images/profile_white.png';
import profile_blue from '../../assets/images/profile_blue.png';
import '../../styles/common/navBar.css';

function NavBar() {
  const location = useLocation();

  return (
    <nav>
      <NavLink
        to="/calendar"
        className={({ isActive }) =>
          isActive ? 'active' : 'inActive'
        }
      >
        <div>
          {location.pathname === '/calendar' ? (
            <img src={calendar_blue} alt="" />
          ) : (
            <img src={calendar_white} alt="" />
          )}

          <span>먹어쓰</span>
        </div>
      </NavLink>
      <NavLink
        to="/group"
        className={({ isActive }) =>
          isActive ? 'active' : 'inActive'
        }
      >
        <div>
          {location.pathname === '/group' ? (
            <img src={group_blue} alt="" />
          ) : (
            <img src={group_white} alt="" />
          )}
          <span>먹그룹</span>
        </div>
      </NavLink>
      <NavLink
        to="/"
        className={({ isActive }) =>
          isActive ? 'active' : 'inActive'
        }
      >
        <div>
          {location.pathname === '/' ? (
            <img src={home_blue} alt="" />
          ) : (
            <img src={home_white} alt="" />
          )}
          <span>홈</span>
        </div>
      </NavLink>
      <NavLink
        to="/introduce"
        className={({ isActive }) =>
          isActive ? 'active' : 'inActive'
        }
      >
        <div>
          {location.pathname === '/introduce' ? (
            <img src={mbti_blue} alt="" />
          ) : (
            <img src={mbti_white} alt="" />
          )}
          <span>먹BTI</span>
        </div>
      </NavLink>
      <NavLink
        to="/profile"
        className={({ isActive }) =>
          isActive ? 'active' : 'inActive'
        }
      >
        <div>
          {location.pathname === '/profile' ? (
            <img src={profile_blue} alt="" />
          ) : (
            <img src={profile_white} alt="" />
          )}
          <span>내정보</span>
        </div>
      </NavLink>
    </nav>
  );
}

export default NavBar;
