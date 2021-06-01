import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import SubjectsList from './professor/components/SubjectsList';
import SubjectEdit from "./professor/components/SubjectEdit";
import 'bootstrap/dist/css/bootstrap.min.css';
import SubjectStudents from "./SubjectStudents";
import SignUp from "./auth/components/SignUp";
import Profile from "./auth/components/Profile";
import StudentCourses from "./student/components/StudentCourses";
import StudentSchedule from "./student/components/StudentSchedule";
import StudentEnroll from "./student/components/StudentEnroll";

class App extends Component {
  render() {
    return (
        <div className="page-back-color">
        <div className="align-page components-back-color">
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/register' component={SignUp}/>
            <Route path='/profile' component={Profile}/>
            <Route path='/professor/courses' exact={true} component={SubjectsList}/>
            <Route path='/subjects/:id' component={SubjectEdit}/>
            <Route path='/professor/subject/:id/students' component={SubjectStudents}/>
            <Route path='/student/courses' component={StudentCourses}/>
            <Route path='/student/enroll' component={StudentEnroll}/>
            <Route path='/student/schedule' component={StudentSchedule}/>
          </Switch>
        </Router>
        </div>
        </div>
    )
  }
}

export default App;
