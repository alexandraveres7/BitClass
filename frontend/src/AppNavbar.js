import React, {Component} from 'react';
import './AppNavbar.css';
import './App.css'
import {NavLink, NavItem, Navbar, Nav} from "react-bootstrap";
import AuthenticationService from "./auth/services/AuthenticationService";
import { Link } from 'react-router-dom';
import {NavbarText} from "reactstrap";
import { ReactComponent as Logo } from './bitclass.svg';

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);

        this.state = {
            showStudent: false,
            showProfessor: false,
            username: undefined,
            login: false
        };
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    componentDidMount() {
        const user = AuthenticationService.getCurrentUser();

        if (user) {
            const role = user.role;

            this.setState({
                showStudent: role.includes("ROLE_STUDENT"),
                showProfessor: role.includes("ROLE_PROFESSOR"),
                login: true,
                username: user.username
            });
        }
    }

    signOut = () => {
        AuthenticationService.signOut();
        this.props.history.push('/');
        window.location.reload();
    }

    render() {
        return (<div className="color-nav">
                <Navbar collapseOnSelect expand="sm" variant="light">
                    <Navbar.Brand tag={Link} to="/"/>
                    <div style={{height: '20', width: '25'}}>
                    <Logo/>
                    </div>
                    <Nav className="mr-auto">
                        {this.state.showStudent && <NavLink style={{color: '#000000'}} href="/student">Student</NavLink>}
                        {this.state.showProfessor && <NavLink style={{color: '#000000'}} href="/professor">Professor</NavLink>}
                    </Nav>
                    <Navbar.Toggle onClick={this.toggle} aria-controls="responsive-navbar-nav"/>
                    <Navbar.Collapse isOpen={this.state.isOpen} id="responsive-navbar-nav">
                        {
                            this.state.login ? (
                                <Nav className="mr-auto">
                                    <NavItem>
                                        <NavbarText>
                                            Signed in as: <a href="/profile">{this.state.username}</a>
                                        </NavbarText>
                                    </NavItem>
                                    {this.state.showStudent && <NavLink style={{color: '#000000'}} href="/student/courses">Courses</NavLink>}
                                    <NavItem className="nav-item-space">
                                        <NavLink href="/" onClick={this.signOut}>SignOut</NavLink>
                                    </NavItem>
                                </Nav>
                                ): (
                                <Nav className="mr-auto">
                                </Nav>
                            )
                        }
                    </Navbar.Collapse>
                </Navbar>
            </div>
        )
    }
}

