import React, { Component } from 'react';
import AppNavbar from "../../AppNavbar";
import { Container } from 'reactstrap';
import { Button, Form, FormGroup, Input, Label, Row, Col } from "reactstrap";
import { Alert } from "react-bootstrap"

import AuthenticationService from '../services/AuthenticationService'

const validEmailRegex = RegExp(/^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i);


const validateForm = (errors) => {
    let valid = true;
    Object.values(errors).forEach(
        (val) => val.length > 0 && (valid = false)
    );
    return valid;
}

class SignUp extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: "",
            username: "",
            email: "",
            password: "",
            message: "",
            successful: false,
            validForm: true,
            errors: {
                name: '',
                username: '',
                email: '',
                password: ''
            }
        };
    }

    changeHandler = (event) => {
        let target_name = event.target.name;
        let value = event.target.value;

        let errors = this.state.errors;

        switch (target_name) {
            case 'name':
                errors.name =
                    value.length < 6
                        ? 'Name must be at least 6 characters long!'
                        : '';
                break;
            case 'username':
                errors.username =
                    value.length < 5
                        ? 'Username must be at least 5 characters long!'
                        : '';
                break;
            case 'email':
                errors.email =
                    validEmailRegex.test(value)
                        ? ''
                        : 'Email is not valid!';
                break;
            case 'password':
                errors.password =
                    value.length < 8
                        ? 'Password must be 8 characters long!'
                        : '';
                break;
            default:
                break;
        }

        this.setState({errors, [target_name]: value}, ()=> {
            console.log(errors)
        })
    }

    signUp = (e) => {
        e.preventDefault();
        const valid = validateForm(this.state.errors);
        this.setState({validForm: valid});
        if(valid){
            AuthenticationService.register(
                this.state.name,
                this.state.username,
                this.state.email,
                this.state.password
            ).then(
                response => {
                    this.setState({
                        message: response.data,
                        successful: true
                    });
                    setTimeout(() => {
                        this.props.history.push("/");
                    }, 2000)
                },
                error => {
                    console.log("Fail! Error = " + error.toString());

                    this.setState({
                        successful: false,
                        message: error.toString()
                    });
                }
            );
        }
    }

    render() {
        const title = <h2>Register User</h2>;
        const errors = this.state.errors;

        let alert = "";

        const alert_style = {
            marginTop: '30px'
        };

        const button = {
            width: '40%',
            height: '30%',
            justifyContent: 'center',
            alignItems: 'center',
            marginLeft: '0%',
            marginTop: '5%',
            background: '#ffff00',
            color: 'black',
            fontWeight: 'bold'
        };

        if(this.state.message){
            if(this.state.successful){
                alert = (
                    <Alert variant="success">
                        {this.state.message}
                    </Alert>
                );
            }else{
                alert = (
                    <Alert variant="danger">
                        {this.state.message}
                    </Alert>
                );
            }
        }

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <Row>
                        <Col sm="12" md={{ size: 4, offset: 4 }}>
                            {title}
                            <Form onSubmit={this.signUp}>
                                <FormGroup controlId="name">
                                    <Label for="name">Name</Label>
                                    <Input
                                        type="text"
                                        placeholder="Enter Name"
                                        name="name" id="name"
                                        value={this.state.name}
                                        autoComplete="name"
                                        onChange={this.changeHandler}
                                    />
                                    {
                                        errors.name && (
                                            <Alert variant="danger">
                                                {errors.name}
                                            </Alert>
                                        )
                                    }
                                </FormGroup>

                                <FormGroup controlId="forUsername">
                                    <Label for="username">Username</Label>
                                    <Input
                                        type="text"
                                        placeholder="Enter Username"
                                        name="username" id="username"
                                        value={this.state.username}
                                        autoComplete="username"
                                        onChange={this.changeHandler}
                                    />
                                    {
                                        errors.username && (
                                            <Alert variant="danger">
                                                {errors.username}
                                            </Alert>
                                        )
                                    }
                                </FormGroup>

                                <FormGroup controlId="formEmail">
                                    <Label for="email">Email</Label>
                                    <Input required
                                           type="text"
                                           placeholder="Enter Email"
                                           name="email" id="email"
                                           value={this.state.email}
                                           autoComplete="email"
                                           onChange={this.changeHandler}
                                    />
                                    {
                                        errors.email && (
                                            <Alert variant="danger">
                                                {errors.email}
                                            </Alert>
                                        )
                                    }
                                </FormGroup>

                                <FormGroup controlId="formPassword">
                                    <Label for="password">Password</Label>
                                    <Input required
                                           type="password"
                                           placeholder="Enter Password"
                                           name="password" id="password"
                                           value={this.state.password}
                                           autoComplete="password"
                                           onChange={this.changeHandler}
                                    />
                                    {
                                        errors.password && (
                                            <Alert key="errorspassword" variant="danger">
                                                {errors.password}
                                            </Alert>
                                        )
                                    }
                                </FormGroup>

                                <Button style={button} variant="primary" type="submit">
                                    Create
                                </Button>
                                {
                                    !this.state.validForm && (
                                        <Alert key="validForm" variant="danger">
                                            Please check the inputs again!
                                        </Alert>
                                    )
                                }
                                <div style={alert_style}>
                                {alert}
                                </div>

                            </Form>
                        </Col>
                    </Row>
                </Container>
            </div>);
    }
}

export default SignUp;