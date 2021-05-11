import React, { Component } from 'react';
import BackendService from "../../auth/services/BackendService";
import AppNavbar from "../../AppNavbar";
import './StudentEnroll.css'
import {Button} from "reactstrap";

class StudentEnroll extends Component{
    constructor(props){
        super(props)
        this.state = {
            subjects: [],
            checkedItems: {},
            count: 0,
        };
    }

    componentDidMount() {
        const response = BackendService.getAllCourses();
        response.then(data => this.setState({subjects: data}));
    }

    handleChange = (event, formKey) => {
        const { name, checked } = event.target;
        const updatedCheckedItems = { ...this.state.checkedItems, [name]: checked };

        this.setState({
            checkedItems: updatedCheckedItems,
            count: Object.values(updatedCheckedItems).filter((value) => value).length,
        });
    };

    handleCoursesSubmit (subjects) {
        BackendService.enrollStudent(subjects).then(response => {
            if (!response.ok) {
               return (
                   <h1> Enrollment unsuccessful</h1>
               )
            } else {
                this.props.history.push("/student/courses");
            }
        })
    }

    render = () => {
        const checkedValues = { ...this.state.checkedItems };
        const checkedCount = Object.values(checkedValues).filter((value) => value)
            .length;

        console.log('CIAO CIAO', )


        const subjects = this.state.subjects;

        return (
            <div>
                <AppNavbar/>
                <div className="check">
                {subjects.map((item, index) => (
                    <label className='container' key={item.id}>
                        <input
                            type={`checkbox`}
                            name={item.name}
                            checked={this.state.checkedItems[item.name] || false}
                            onChange={this.handleChange}
                            disabled={!checkedValues[item.name] && checkedCount >= 1}
                        />
                        <span className="checkmark">{item.name}</span>
                    </label>
                ))}
                </div>
                <Button type='submit' onClick={this.handleCoursesSubmit(Object.keys(this.state.checkedItems))}>Enroll</Button>
            </div>
        );
    };
}

export default StudentEnroll;