import React, {useEffect, useState} from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import "./App.css";

import AuthService from "./services/AuthService";

import Login from "./components/Login";
import Register from "./components/Register";
import Profile from "./components/Profile";
import BoardUser from "./components/BoardUser";
import BoardAdmin from "./components/BoardAdmin";
import Converter from "./components/Converter";

export default function App(){
  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);

  const logOut = () => {
    AuthService.logout();
  }

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user){
      setCurrentUser(user);
      setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
    }
  }, [])

  return (
      <Router>
        <div>
          <nav className="navbar navbar-expand navbar-dark bg-dark">
            <Link to={"/"} className="navbar-brand">
              SCC
            </Link>
            <div className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link to={"/converter"} className="nav-link">
                  Converter
                </Link>
              </li>

              {showAdminBoard && (
                  <li className="nav-item">
                    <Link to={"/admin"} className="nav-link">
                      Admin Board
                    </Link>
                  </li>
              )}

              {currentUser && (
                  <li className="nav-item">
                    <Link to={"/user"} className="nav-link">
                      User
                    </Link>
                  </li>
              )}
            </div>

            {currentUser ? (
                <div className="navbar-nav ml-auto">
                  <li className="nav-item">
                    <Link to={"/profile"} className="nav-link">
                      {currentUser.username}
                    </Link>
                  </li>
                  <li className="nav-item">
                    <a href="/logout" className="nav-link" onClick={logOut}>
                      Logout
                    </a>
                  </li>
                </div>
            ) : (
                <div className="navbar-nav ml-auto">
                  <li className="nav-item">
                    <Link to={"/login"} className="nav-link">
                      Login
                    </Link>
                  </li>

                  <li className="nav-item">
                    <Link to={"/register"} className="nav-link">
                      Sign Up
                    </Link>
                  </li>
                </div>
            )}
          </nav>

          <div className="container mt-3">
            <Switch>
              <Route exact path={["/", "/converter"]} component={Converter} />
              <Route exact path="/login" component={Login} />
              <Route exact path="/register" component={Register} />
              <Route exact path="/profile" component={Profile} />
              <Route path="/user" component={BoardUser} />
              <Route path="/admin" component={BoardAdmin} />
            </Switch>
          </div>
        </div>
      </Router>
  );
}
