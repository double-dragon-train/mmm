import { Outlet } from "react-router-dom";
import NavBar from "./NavBar";

function Layout() {
    return (
        <div>
          <main>
            <Outlet/>
          </main>
          <NavBar />
        </div>
      );
    
}

export default Layout;
