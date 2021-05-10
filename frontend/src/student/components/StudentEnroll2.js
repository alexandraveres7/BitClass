import React, { Component } from 'react';
import BackendService from "../../auth/services/BackendService";
import AppNavbar from "../../AppNavbar";

class StudentEnroll2 extends Component{
    constructor(props){
        super(props)
        this.state = {
            subjects: [],
            checkedItems: new Map()
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        const response = BackendService.getAllCourses();
        response.then(data => this.setState({subjects: data}));
    }

    handleChange(event) {
        var isChecked = event.target.checked;
        var item = event.target.value;

        this.setState(prevState => ({ checkedItems: prevState.checkedItems.set(item, isChecked) }));
    }

    handleSubmit(event) {
        console.log(this.state);
        event.preventDefault();
    }

    render() {
        return (
            <div>
                <AppNavbar/>
                <h1> Examples of Multiple Checkbox in React </h1>

                <form onSubmit={this.handleSubmit}>

                    {
                        this.state.subjects.map(item => (
                            <li>
                                <label>
                                    <input
                                        type="checkbox"
                                        value={item.id}
                                        onChange={this.handleChange}
                                    /> {item.name}
                                </label>
                            </li>
                        ))
                    }

                    <br/>
                    <input type="submit" value="Submit" />
                </form>
            </div>
        );
    }
}
export default StudentEnroll2;