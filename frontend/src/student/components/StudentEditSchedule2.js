import React, {Component } from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import StudentService from "../services/StudentService";
import "./StudentEditSchedule2.css"
import {Button, ButtonGroup} from "reactstrap";
import {Link} from "react-router-dom";
import {add} from "winston";

class StudentEditSchedule2 extends Component {

    constructor(props) {
        super(props);
        this.StudentService = StudentService
        this.state = {subjects: [], selectedLabs: [], cellSelected: "", selectedCells: []};
    }

    isCellSelected = (rowIndex, cellIndex, columnIndex) => {
        const equality = this.state.selectedCells.find(cell => {
            const x = cell[0] === rowIndex && cell[1] === cellIndex
            console.log('EQUALITY', x)
            return x
        })
        return equality
    }

    // call la backend sa iau materiilor la care e inscris studentul
    componentDidMount() {
        const result = this.StudentService.getStudentCoursesIds();

        result.then(data => this.setState({subjects: data}));
    }

    // call la backend sa iau laboratoarele de la materiile respective

    render = () => {
        const products = [{"hour": "8-10", "monday": "CES", "friday": "SM"}, {"hour": "10-12", "monday": "", "friday": "SM"},
            {"hour": "12-14", "tuesday": "STD"}, {"hour":"14-16", "thursday": "CES"}, {"hour": "16-18", "thursday": "CES"}];
        const columns = [{
            dataField: 'hour',
            text: 'Lab hour',
            headerStyle:  { backgroundColor: '#000000', color: '#ffffff', textAlign: 'center'},
            style: { backgroundColor: '#000000', color: '#ffffff', textAlign: 'center' },
            events: {
                onClick: (e, column, columnIndex, row, rowIndex) => {
                    const selectedLab = e.target.innerText
                    console.log('SELECTED OPTION', selectedLab)
                }
            },
            formatter: (cell, row) => <div className={this.state.cellSelected} > {cell} </div>
        },
            {
                dataField: 'monday',
                text: 'Monday',
                headerStyle:  { backgroundColor: '#FFFF00', color: '#000000', textAlign: 'center'},
                style: {textAlign: 'center'},
                events: {
                    onClick: (e, column, columnIndex, row, rowIndex) => {
                        const arrayIndex = this.state.selectedCells.indexOf([rowIndex, columnIndex])
                        const isCellSelected = this.isCellSelected(rowIndex, columnIndex)

                        if(!isCellSelected) {
                            this.state.selectedCells.push([rowIndex, columnIndex])
                        } else {
                            console.log('DELETING', arrayIndex)
                            this.state.selectedCells.pop(arrayIndex)
                        }
                        console.log('STATE AFTER SELECT', this.state)
                    }
                },
                formatter: (cell, row, rowIndex, cellIndex, columnIndex) =>
                    <div style={{backgroundColor: this.isCellSelected(rowIndex, cellIndex, columnIndex) === true? 'red': 'black'}} > {cell} </div>
            }, {
                dataField: 'tuesday',
                text: 'Tuesday',
                headerStyle:  { backgroundColor: '#FFFF00', color: '#000000', textAlign: 'center' },
                style: {textAlign: 'center'},
                events: {
                    onClick: (e, column, columnIndex, row, rowIndex) => {
                        console.log(e);
                        console.log(e.target.firstChild.data);
                        // console.log(column);
                        // console.log(columnIndex);
                        // console.log(row);
                        // console.log(rowIndex);
                    }
                },
            }, {
                dataField: 'wednesday',
                text: 'Wednesday',
                headerStyle:  { backgroundColor: '#FFFF00', color: '#000000', textAlign: 'center' },
                style: {textAlign: 'center'}
            }, {
                dataField: 'thursday',
                text: 'Thursday',
                headerStyle:  { backgroundColor: '#FFFF00', color: '#000000', textAlign: 'center' },
                style: {textAlign: 'center'}
            }, {
                dataField: 'friday',
                text: 'Friday',
                headerStyle:  { backgroundColor: '#FFFF00', color: '#000000', textAlign: 'center'},
                style: {textAlign: 'center'},
                events: {
                    onClick: (e, column, columnIndex, row, rowIndex) => {
                        // console.log(e);
                        console.log('Ceai facut tati', e.target.firstChild.data);
                        // console.log(column);
                        // console.log(columnIndex);
                        // console.log(row);
                        // console.log(rowIndex);
                        console.log('STATE', this.state)
                        return <div className={this.state.cellSelected} onClick={this.onSelectCell} > {"wtf"} </div>
                    }
                },
                formatter: (cell, row) => {
                    if(this.state.cellSelected === "selected") {
                        return <div className={"selected"} onClick={this.onSelectCell} > {cell} </div>
                    }
                    return <div className={"unselected"} onClick={this.onSelectCell} > {cell} </div>
                }
            },];

        const selectRow = {
            mode: 'cells',
            onSelect: (row, isSelect, rowIndex, e) => {
                // ...
            }
        };

        return(
            <div className='bb'>
                <BootstrapTable keyField='id' data={ products } columns={ columns }/>
                <Button className='butonas' size="sm" color="primary" tag={Link} to={""}>Submit</Button>
                <div className={this.state.cellSelected}>CIAOOOOO</div>
            </div>
        )
    }
}

export default StudentEditSchedule2