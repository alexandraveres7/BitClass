import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import SubjectsList from './SubjectsList';
import SubjectEdit from "./SubjectEdit";
import 'bootstrap/dist/css/bootstrap.min.css';
import SubjectStudents from "./SubjectStudents";

class App extends Component {
  render() {
    return (
        <div className="align-page">
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/subjects' exact={true} component={SubjectsList}/>
            <Route path='/subjects/:id' component={SubjectEdit}/>
            <Route path='/subject/:id/students' component={SubjectStudents}/>
          </Switch>
        </Router>
        </div>
    )
  }
}

export default App;
