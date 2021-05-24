import React, {Component} from "react";
import StudentEditSchedule from "./StudentEditSchedule";
import AppNavbar from "../../AppNavbar";

class StudentSchedule extends Component {
    constructor(props) {
        super(props);
        this.state = {
            content: "",
            error: ""
        }
    }

    render = () => {
        return(
            <div>
                <AppNavbar/>
                <StudentEditSchedule/>
            </div>
        )
    }
}

export default StudentSchedule;