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
import Layout from './components/common/Layout';
import GroupPage from './pages/GroupPage';

const queryClient = new QueryClient();

const router = createBrowserRouter([
  {
    path: '/',
    element: <LandingPage />,
    children: [],
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
    path: '/profile',
    element: <ProfilePage />,
  },
  {
    path: '/mbti/:mbtiId',
    element: <MbtiPage />,
  },
  {
    path: '/main',
    element: <Layout />,
    children: [
      {
        path: '/main/group',
        element: <GroupPage />,
      },
    ],
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
