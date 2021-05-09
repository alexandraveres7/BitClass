import ApiHelper from "./ApiHelper";
import React, {Component} from "react";
import AppNavbar from "./AppNavbar";
import {Container, Table} from "reactstrap";
import "./SubjectStudents.css";


class SubjectStudents extends Component{

    constructor(props) {
        super(props);
        this.state = {students: [], areAllocated: false};
        this.ApiHelper = new ApiHelper();
    }

    componentDidMount() {
        const path = this.props.location.pathname;
        const response = this.ApiHelper.get_students(path);
        if (response.length > 0){
            this.setState({areAllocated: true})
        }
        response.then(data => this.setState({students: data}));
    }

    render() {
        const {students, areAllocated} = this.state;
        console.log(areAllocated);
        if (!areAllocated) {
            return (
                <div>
                    <AppNavbar/>
                    <div className="text-center top-buffer">
                    <div className="alert alert-danger" role="alert">
                    No students have enrolled so far!
                    </div>
                </div>

                </div>
            );
        }
        const studentsList = students.map(student => {
            const name = `${student.name}`;
            const grade = `${student.grade}`;
            return <tr key={student.id}>
                <td style={{whiteSpace: 'nowrap'}}>{name}</td>
                <td>{grade}</td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <h3>Students Subjects</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Name</th>
                            <th width="30%">Grade</th>
                        </tr>
                        </thead>
                        <tbody>
                        {studentsList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default SubjectStudents