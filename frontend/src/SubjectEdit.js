import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './Navbar';
import ApiHelper from "./ApiHelper";
import "./SubjectEdit.css"

class SubjectEdit extends Component {

    emptyItem = {
        name: '',
        description: '',
        assistantName: '',
        assistantEmail: '',
        places: '',
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.ApiHelper = new ApiHelper();
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const path = `/v1/subject/${this.props.match.params.id}`
            const subject = await this.ApiHelper.get(path)
            this.setState({item: subject});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
        await this.ApiHelper.edit_subject("/v1/subject", item)
        this.props.history.push('/subjects');
    }

    render() {
        const {item} = this.state;
        const title = <h2 class="yellow-text">{item.id ? 'Edit Subject' : 'Add Subject'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <div className="row">
                        <FormGroup className="col-md-4 mb-3">
                            <Label for="name">Name</Label>
                            <Input type="text" name="name" id="name" value={item.name || ''}
                                   onChange={this.handleChange} autoComplete="name"/>
                        </FormGroup>
                        <FormGroup className="col-md-2 mb-3">
                            <Label for="places">Places</Label>
                            <Input type="number" name="places" id="places" value={item.places || ''}
                                   onChange={this.handleChange} autoComplete="places"/>
                        </FormGroup>
                    </div>
                    <FormGroup>
                        <Label for="description">Description</Label>
                        <Input type="text" name="description" id="description" value={item.description || ''}
                               onChange={this.handleChange} autoComplete="description"/>
                    </FormGroup>
                    <div className="row">
                        <FormGroup className="col-md-4 mb-3">
                            <Label for="assistant">Assistant name</Label>
                            <Input type="text" name="assistantName" id="assistantName" value={item.assistantName || ''}
                                   onChange={this.handleChange} autoComplete="assistant name"/>
                        </FormGroup>
                        <FormGroup className="col-md-4 mb-3">
                            <Label for="assistant">Assistant email</Label>
                            <Input type="text" name="assistantEmail" id="assistantEmail"
                                   value={item.assistantEmail || ''}
                                   onChange={this.handleChange} autoComplete="assistant email"/>
                        </FormGroup>
                    </div>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/subjects">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(SubjectEdit);