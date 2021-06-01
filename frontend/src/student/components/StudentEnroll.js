import React, { Component } from 'react';
import BackendService from "../services/StudentService";
import Button from 'react-bootstrap/Button';
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

    calculateProbability() {
        const average_gpa = localStorage.getItem("gpa");
        if (average_gpa > 8 && average_gpa <= 8.5){
            return 25;
        }
        else if (average_gpa > 8.5 && average_gpa <= 9){
            return 50;
        }
        else if (average_gpa >9 && average_gpa <= 9.5){
            return 75;
        }
        else if (average_gpa > 9.5 && average_gpa <= 10){
            return 100;
        }
    }


    render = () => {
        const checkedValues = { ...this.state.checkedItems };
        const checkedCount = Object.values(checkedValues).filter((value) => value)
            .length;

        const subjects = this.state.subjects;
        const probability = this.calculateProbability();

        return (
            <div>
                <AppNavbar/>
                <div className="checkbox-title">
                    <h4> Choose 4 courses from below:</h4>
                </div>
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
                    </label>
                ))}
                </div>
                <div className='button-submit'>
                <Button className='but' variant="outline-primary" type='submit' onClick={() => this.handleCoursesSubmit(Object.keys(this.state.checkedItems))}>Enroll</Button>
                </div>
                <div className='cbb'>
                    <a>Probability to enter all selected courses based on last year's average grade:</a>
                    <a className='prob'> {probability}%</a>
                </div>
            </div>


        );
    };
}

export default StudentEnroll;