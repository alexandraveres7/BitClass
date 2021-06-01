import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {Button, Container} from 'reactstrap';
import {Alert, Card} from "react-bootstrap"
import "./Profile.css"
import image from '../../square.png';

import AuthenticationService from '../services/AuthenticationService';
import AppNavbar from "../../AppNavbar";

class Profile extends Component {

    constructor(props) {
        super(props);
        this.state = {user: undefined};
    }

    componentDidMount() {
        const user = AuthenticationService.getCurrentUser();
        this.setState({user: user});
    }

    getRandomArbitrary(min, max) {
        return Math.random() * (max - min) + min;
    }

    render() {
        let userInfo;
        const user = this.state.user;

        const usr = AuthenticationService.getCurrentUser();
        const role = usr.role;

        var average_gpa = localStorage.getItem("gpa");

        var is_role_professor = (role.includes("ROLE_PROFESSOR")) ? "Professor" : "Student";
        var occupation_or_year = (role.includes("ROLE_PROFESSOR")) ? "Occupation: Head of works" : "3rd year average grade: " + average_gpa;

        // login
        if (user && user.accessToken) {

          userInfo = (
              <div className="card-usr">
                  <div className="container-fluid">
                      <div className="row">
                          <div className="col-12 mt-3">
                              <div className="card">
                                  <div className="card-horizontal">
                                      <div className="img-square-wrapper">
                                          <img src={image}
                                               alt="Card image cap"/>
                                      </div>

                                      <div className="card-body">
                                          <h4 className="card-title">{is_role_professor}</h4>
                                          <div className="bla">
                                          <p className="card-text">Username: {user.username}</p>
                                          <p className="card-text">Email: {user.email}</p>
                                          <p className="card-text">{occupation_or_year}</p>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
            );
        }
        else { // not login
            userInfo = <div style={{marginTop: "20px"}}>
                <Alert variant="primary">
                    <h2>Profile Component</h2>
                    <Button color="success"><Link to="/"><span style={{color: "white"}}>Login</span></Link></Button>
                </Alert>
            </div>
        }

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    {userInfo}
                </Container>
            </div>
        );
        }
        }

export default Profile;