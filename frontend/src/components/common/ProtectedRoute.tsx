import { Navigate, Outlet } from 'react-router-dom';
import NavBar from './NavBar';
import userStore from '../../stores/userStore';

function ProtectedRoute() {
  const { isLogin } = userStore();
  if (!isLogin) {
    return <Navigate to="/landing" />;
  }

  return (
    <div>
      <Outlet />
      <NavBar />
    </div>
  );
}

export default ProtectedRoute;
