// ui/Thing/index.js
const React = require('react');

function toCheckBox(boolVal) {
    return <div className="custom-control custom-checkbox">
        <input type="checkbox" checked={boolVal} readOnly={true}
               className="custom-control-input" id="customControlValidation1"/>
        <label className="custom-control-label" htmlFor="customControlValidation1"/>
    </div>;
}

export default (props) => {
    const {
        meta
    } = props;
    let htmlFields = meta?.fields?.map((field, index) => {
        return <tr key={index} className="text-center">
            <td>{field.name}</td>
            <td>{toCheckBox(field.key)}</td>
            <td>{field.type}</td>
            <td>{toCheckBox(field.nullable)}</td>
            <td>{toCheckBox(field.immutable)}</td>
        </tr>;
    });
    return (
        <table className="table table-bordered">
            <thead>
            <tr className="table-primary">
                <th colSpan="2">Meta name</th>
                <th colSpan="3" className="text-center">{meta.name}</th>
            </tr>
            <tr className="table-secondary">
                <th colSpan="2">Key Delimiter</th>
                <th colSpan="3" className="text-center">{meta.keyDelimiter}</th>
            </tr>
            <tr>
                <th colSpan="5" className="text-center table-info">Fields</th>
            </tr>
            <tr>
                <th>name</th>
                <th>key</th>
                <th>type</th>
                <th>nullable</th>
                <th>immutable</th>
            </tr>
            </thead>
            <tbody>
            {htmlFields}
            </tbody>
        </table>
    );
};