import './App.css';
import {
  RouterProvider,
  createBrowserRouter,
} from 'react-router-dom';
import LandingPage from './pages/LandingPage';
import SignupPage from './pages/SignupPage';
import LoginPage from './pages/LoginPage';
import ProfilePage from './pages/ProfilePage';
import MbtiPage from './pages/MbtiPage';
import {
  QueryClient,
  QueryClientProvider,
} from '@tanstack/react-query';
import GroupPage from './pages/GroupPage';
import ProtectedRoute from './components/common/ProtectedRoute';
import MainPage from './pages/MainPage';
import CalendarPage from './pages/CalendarPage';
import IntroducePage from './pages/IntroducePage';

const queryClient = new QueryClient();

const router = createBrowserRouter([
  {
    path: '/',
    element: <ProtectedRoute />,
    children: [
      {
        path: '/',
        element: <MainPage />,
      },
      {
        path: '/group',
        element: <GroupPage />,
      },
      {
        path: '/calendar',
        element: <CalendarPage />,
      },
      {
        path: '/profile',
        element: <ProfilePage />,
      },
      {
        path: '/introduce',
        element: <IntroducePage />,
      },
    ],
  },
  {
    path: '/landing',
    element: <LandingPage />,
  },
  {
    path: '/signup',
    element: <SignupPage />,
  },
  {
    path: '/login',
    element: <LoginPage />,
  },
  {
    path: '/mbti/:mbtiId',
    element: <MbtiPage />,
  },
]);

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router} />
    </QueryClientProvider>
  );
}

export default App;
