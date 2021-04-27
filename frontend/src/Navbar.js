import React, { Component } from 'react';
import './Navbar.css';
import { Navbar, Nav } from "react-bootstrap";

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return ( <div className="color-nav">
            <Navbar collapseOnSelect expand="sm" variant="light">
                <Navbar.Brand href="/"/>
                <img
                    src="bitclass.svg"
                    width="150"
                    height="150"
                    className="d-inline-block align-top"
                    alt="BitCLASS Logo"
                />
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link style={{ color: '#000' }} href="/">Schedule</Nav.Link>
                        <Nav.Link style={{ color: '#000' }} href="/subjects">Courses</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        </div>
        )
    }
}

