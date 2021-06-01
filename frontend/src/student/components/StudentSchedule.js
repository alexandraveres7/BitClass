import React, {Component} from "react";
import StudentEditSchedule2 from "./StudentEditSchedule2";
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
                <StudentEditSchedule2/>
            </div>
        )
    }
}

export default StudentSchedule;