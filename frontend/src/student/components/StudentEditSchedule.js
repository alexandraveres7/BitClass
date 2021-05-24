import React, { useState } from 'react';

import { EditingState } from '@devexpress/dx-react-grid';
import {
    Grid,
    Table,
    TableHeaderRow,
    TableInlineCellEditing,
    Toolbar,
} from '@devexpress/dx-react-grid-bootstrap4';
import '@devexpress/dx-react-grid-bootstrap4/dist/dx-react-grid-bootstrap4.css';
import {
    Plugin,
    Template,
    TemplatePlaceholder,
} from '@devexpress/dx-react-core';

import {
    generateRows,
    defaultColumnValues,
} from '../datagenerator/generate.js';

const getRowId = row => row.id;

const StartEditActionSelector = (props) => {
    const { defaultAction, changeAction } = props;
    return (
        <div>
            {'Start Edit Action:'}
            &nbsp;
            <select
                defaultValue={defaultAction}
                onChange={e => changeAction(e.target.value)}
                className="dropdown"
            >
                <option value="click">Click</option>
                <option value="doubleClick">Double Click</option>
            </select>
        </div>
    );
};

const SelectTextChecker = (props) => {
    const { isSelectText, changeSelectText } = props;
    return (
        <div
            style={{
                padding: '0em 1em',
            }}
        >
            <label htmlFor="selectTextChecker" className="form-check-label">
                <input
                    type="checkbox"
                    checked={isSelectText}
                    id="selectTextChecker"
                    name="selectTextChecker"
                    className="form-check-input"
                    onChange={e => changeSelectText(e.target.checked)}
                />
                Select Text On Focus
            </label>
        </div>
    );
};

const EditPropsPanel = props => (
    <Plugin name="EditPropsPanel">
        <Template name="toolbarContent">
            <SelectTextChecker {...props} />
            <TemplatePlaceholder />
            <StartEditActionSelector {...props} />
        </Template>
    </Plugin>
);

const FocusableCell = ({ onClick, ...restProps }) => (
    <Table.Cell {...restProps} tabIndex={0} onFocus={onClick} />
);

export default () => {
    const [columns] = useState([
        {name: 'hours', title: "Hours"},
        { name: 'monday', title: 'Monday' },
        { name: 'tuesday', title: 'Tuesday' },
        { name: 'wednesday', title: 'Wednesday' },
        { name: 'thursday', title: 'Thursday' },
        { name: 'friday', title: 'Friday'},
    ]);
    const [rows, setRows] = useState(generateRows({
        columnValues: { id: ({ index }) => index, ...defaultColumnValues },
        length: 6,
    }));
    const [startEditAction, setStartEditAction] = useState('click');
    const [selectTextOnEditStart, setSelectTextOnEditStart] = useState(true);

    const commitChanges = ({ added, changed, deleted }) => {
        let changedRows;
        if (added) {
            const startingAddedId = rows.length > 0 ? rows[rows.length - 1].id + 1 : 0;
            changedRows = [
                ...rows,
                ...added.map((row, index) => ({
                    id: startingAddedId + index,
                    ...row,
                })),
            ];
        }
        if (changed) {
            changedRows = rows.map(row => (changed[row.id] ? { ...row, ...changed[row.id] } : row));
        }
        if (deleted) {
            const deletedSet = new Set(deleted);
            changedRows = rows.filter(row => !deletedSet.has(row.id));
        }
        setRows(changedRows);
    };

    return (
        <div className="card">
            <Grid
                rows={rows}
                columns={columns}
                getRowId={getRowId}
            >
                <EditingState onCommitChanges={commitChanges} />
                <Table cellComponent={FocusableCell} />
                <TableHeaderRow />
                <Toolbar />
                <EditPropsPanel
                    defaultAction={startEditAction}
                    changeAction={setStartEditAction}
                    isSelectText={selectTextOnEditStart}
                    changeSelectText={setSelectTextOnEditStart}
                />
                <TableInlineCellEditing
                    startEditAction={startEditAction}
                    selectTextOnEditStart={selectTextOnEditStart}
                />
            </Grid>
        </div>
    );
};
