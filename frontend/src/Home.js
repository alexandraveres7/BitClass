import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import Login from "./auth/components/Login";
import { ReactComponent as Logo } from './bitclass.svg';
import './Home.css'
import '../src/auth/components/Login.css'
class Home extends Component {


    render() {
        const button = {
            width: '30%',
            height: '30%',
            justifyContent: 'center',
            alignItems: 'center',
            marginLeft: '35%',
            background: '#00bcd4'
        };
        return (
            <div>
                <Container fluid>
                   <Logo className="logo"/>
                    <div className="hr">
                        <hr/>
                    </div>
                    <span className="subtitle">Making Uni days easier</span>
                    <div className="inner">
                        <Login/>
                    </div>

                    <h3 className="pos-txt"> Don't have an account?</h3>
                    <Button style={button} ><Link to="/register"><span style={{color:"white"}}>Sign up for BitCLASS</span></Link></Button>
                </Container>
            </div>
        );
    }
}

export default Home;