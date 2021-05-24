import AppNavbar from '../../AppNavbar';
import React, { Component } from 'react';
import {Container, Table} from 'reactstrap';
import { Link } from 'react-router-dom';
import StudentService from '../services/StudentService';

class StudentCourses extends Component{

    constructor(props) {
        super(props);
        this.state = {subjects: [], areAllocated: true};
    }

    componentDidMount() {
        const response = StudentService.getStudentCourses();
        let count = Object.keys(response).length;
        console.log(count);

        if (count > 0){
            this.setState({areAllocated: true});
        }
        response.then(data => this.setState({subjects: data}));
    }

    render() {
        const {subjects, areAllocated} = this.state;

        const jsonQuery = require('json-query');
        
        console.log(areAllocated);
        if (!areAllocated) {
            return (
                <div>
                    <AppNavbar/>
                    <div className="text-center top-buffer">
                        <div className="alert alert-danger" role="alert">
                            You are not enrolled to any courses!
                        </div>
                        <Link to="/student/enroll" className="btn btn-primary">Enroll now</Link>
                    </div>

                </div>
            );
        }

        const subjectsList = subjects.map((subject) => {
            console.log(subject)
            const name = `${subject.name || ''}`;
            const description = `${subject.description || ''}`
            const assistant_name = `${subject.assistantName || ''}`;
            const professor = jsonQuery('[professor][name]', {data: [subject]}).value;
            return <tr key={subject.id}>
                <td style={{whiteSpace: 'nowrap'}}>{name}</td>
                <td>{description}</td>
                <td>{assistant_name}</td>
                <td>{professor}</td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <h3>Year 4 - Subjects</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Description</th>
                            <th width="15%">Assistant name</th>
                            <th width="20%">Professor</th>
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