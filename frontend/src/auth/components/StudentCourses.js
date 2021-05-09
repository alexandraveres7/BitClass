import AppNavbar from '../../AppNavbar';
import React, { Component } from 'react';
import {Container, Table} from 'reactstrap';
import BackendService from '../services/BackendService';
import ApiHelper from "../../ApiHelper";
import Button from "bootstrap";

class StudentCourses extends Component{

    constructor(props) {
        super(props);
        this.state = {subjects: [], areAllocated: false};
        this.ApiHelper = new ApiHelper();
    }

    componentDidMount() {
        const path = this.props.location.pathname;
        const response = BackendService.getStudentCourses();
        if (response.length > 0){
            this.setState({areAllocated: true})
        }
        response.then(data => this.setState({subjects: data}));
    }

    render() {
        const {subjects, areAllocated} = this.state;

        var jsonQuery = require('json-query');

        console.log(areAllocated);
        if (!areAllocated) {
            return (
                <div>
                    <AppNavbar/>
                    <div className="text-center top-buffer">
                        <div className="alert alert-danger" role="alert">
                            You have not enrolled to any courses!
                        </div>
                        <Button onClick='/student/enroll'>Enroll now</Button>
                    </div>

                </div>
            );
        }
        const subjectsList = subjects.map(subject => {
            const name = `${subject.name}`;
            const description = `${subject.description}`
            const assistant_name = `${subject.assistantName}`;
            const assistant_email = `${subject.assistantEmail}`;
            const professor = jsonQuery('subject.professor.name', {data: subject})
            return <tr key={subject.id}>
                <td style={{whiteSpace: 'nowrap'}}>{name}</td>
                <td>{description}</td>
                <td>{assistant_name}</td>
                <td>{assistant_email}</td>
                <td>{professor}</td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <h3>Subjects</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Name</th>
                            <th width="20%">Description</th>
                            <th width="30%">Assistant name & email</th>
                            <th width="30%">Professor</th>
                        </tr>
                        </thead>
                        <tbody>
                        {subjectsList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}
export default StudentCourses;