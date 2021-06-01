import React, {Component} from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../../AppNavbar';
import { Link } from 'react-router-dom';
import ApiHelperService from "../services/ApiHelperService";
import "./SubjectsList.css";

class SubjectsList extends Component{

    constructor(props) {
        super(props);
        this.state = {subjects: [], isLoading: true};
        this.remove = this.remove.bind(this);
        this.ApiHelper = new ApiHelperService()
    }

    componentDidMount() {
        this.setState({isLoading: true});
        const user = JSON.parse(localStorage.getItem('user'));
        const professor_id = user.id;
        console.log(professor_id);
        const response = this.ApiHelper.get(`/v1/subjects/${professor_id}`);
        console.log(response);
        response.then(data => this.setState({subjects: data, isLoading: false}));
    }

    async remove(id) {
       this.ApiHelper.delete(`/v1/subject/${id}`).then(() => {
           let updatedSubjects = [...this.state.subjects].filter(i => i.id !== id);
           this.setState({subjects: updatedSubjects});
       });
    }

    render() {
        const {subjects, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const subjectsList = subjects.map(subject => {
            const description = `${subject.description || ''}`;
            const professor = `${subject.assistantName || ''}`;
            const places = `${subject.places || ''}`;
            const subj_id = `${subject.id}`;

            return <tr key={subject.id}>
                <a href={`/subject/${subj_id}/students`}>
                <td  style={{whiteSpace: 'nowrap'}}>{subject.name}</td>
                </a>
                <td>{description}</td>
                <td>{professor}</td>
                <td>{places}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/subjects/" + subject.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(subject.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="yellow-button float-right">
                        <Button color="" tag={Link} to="/subjects/new">Add Subject</Button>
                    </div>
                    <h3>Uni Subjects</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Description</th>
                            <th width="20%">Assistant</th>
                            <th width="10%">Places</th>
                            <th width="10%"/>
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

export default SubjectsList