import './App.css';
import {
  RouterProvider,
  createBrowserRouter,
} from 'react-router-dom';
import LandingPage from './pages/LandingPage';
import SignupPage from './pages/SignupPage';
import LoginPage from './pages/LoginPage';
import MbtiPage from './pages/MbtiPage';
import {
  QueryClient,
  QueryClientProvider,
  
} from '@tanstack/react-query'

const queryClient = new QueryClient()

const router = createBrowserRouter([
  {
    path: '/',
    element: <LandingPage />,
    // children: [
    //   {
    //     index: '',
    //     element: <ProtectedRoutes />,
    //     children: [
    //       { index: true, element: <HomePage /> },
    //       // { path: '/:feedId', element: <HomePage /> },

    // ],
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
