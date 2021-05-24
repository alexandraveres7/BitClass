import React, { Component } from 'react';
import BackendService from "../services/StudentService";
import AppNavbar from "../../AppNavbar";
import './StudentEnroll.css'

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
            if (response.status === 200) {
                   this.props.history.push("/student/courses")
            } else {
                return(
                <h1> Enrollment unsuccessful</h1>
            )
            }
        })
    }

    render = () => {
        const checkedValues = { ...this.state.checkedItems };
        const checkedCount = Object.values(checkedValues).filter((value) => value)
            .length;

        const subjects = this.state.subjects;

        return (
            <div>
                <AppNavbar/>
                <div className="check">
                {subjects.map((item) => (
                    <label className='container' key={item.id}>
                        <input
                            type={`checkbox`}
                            name={item.name}
                            checked={this.state.checkedItems[item.name] || false}
                            onChange={this.handleChange}
                            disabled={!checkedValues[item.name] && checkedCount >= 4}
                        />
                        <span className="checkmark">{item.name}</span>
                        <span>{item.percent}%</span>
                    </label>
                ))}
                </div>
                <button type='submit' onClick={this.handleCoursesSubmit(Object.keys(this.state.checkedItems))}>Enroll</button>
            </div>
        );
    };
}

export default StudentEnroll;