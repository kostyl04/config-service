// ui/Thing/index.js

const React = require('react');

export default (props) => {
    const {
        metas,
        onMetaChange
    } = props;
    let metasHtml = metas.map(meta => {
        return <option key={meta.name}>{meta.name}</option>;
    });

    function onChange(event) {
        onMetaChange(event.target.value);
    }
    return (
        <ul className="nav flex-column">
            <li className="nav-item">
                <select id="meta-nav-select" onChange={onChange}>
                    {metasHtml}
                </select>
            </li>
            <li className="nav-item">
                <button className="btn btn-primary" data-toggle="modal" data-target="#createMetaModal">Create meta
                </button>
            </li>
        </ul>
    );
};