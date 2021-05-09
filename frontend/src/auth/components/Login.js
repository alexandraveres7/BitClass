import React, { Component } from 'react';
import { Container } from 'reactstrap';
import { Form, Alert, FormGroup, Input, Label, Row, Col } from "reactstrap";
import {Button} from 'react-bootstrap';
import AuthenticationService from "../services/AuthenticationService";
import '../../App.css';
import { withRouter } from "react-router-dom";

class Login extends Component {

    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            error: ""
        };
    }

    changeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam]: val});
    }

    doLogin = async (event) => {
        event.preventDefault();

        AuthenticationService
            .login(this.state.username,
                this.state.password)
            .then(
                () => {
                    this.props.history.push('/profile');
                },
                error => {
                    console.log("Login fail: error = { " + error.toString() + " }");
                    this.setState({error: "Invalid credentials"});
                }
            );
    }

    render() {
        return (
            <div>
                <Container>
                    <Row>
                        <Col sm="12">
                            <Form  onSubmit={this.doLogin}>
                                <FormGroup>
                                    <Label for="username"><strong>Username</strong></Label>
                                    <Input autoFocus
                                           type="text"
                                           name="username" id="username"
                                           value={this.state.username}
                                           placeholder="Enter Username"
                                           autoComplete="username"
                                           onChange={this.changeHandler}
                                    />
                                </FormGroup>

                                <FormGroup>
                                    <Label for="password"><strong>Password</strong></Label>
                                    <Input type="password"
                                           name="password" id="password"
                                           value={this.state.password}
                                           placeholder="Enter Password"
                                           autoComplete="password"
                                           onChange={this.changeHandler}
                                    />
                                </FormGroup>

                                <Button className="btn-primary" type="submit" size="lg" block>
                                    LOGIN
                                </Button>
                                {
                                    this.state.error && (
                                        <Alert color="danger">
                                            {this.state.error}
                                        </Alert>
                                    )
                                }
                            </Form>
                        </Col>
                    </Row>
                </Container>
            </div>);
    }
}

export default withRouter(Login);