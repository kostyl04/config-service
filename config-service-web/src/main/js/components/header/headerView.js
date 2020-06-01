// ui/Thing/index.js
const React = require('react');
import {Link} from "react-router-dom";

export default (props) => {
    const {
        applications,
    } = props;
    let applicationsHtml = applications.map(app => {
        return <a className="dropdown-item" value={app} key={app} href="#">{app}</a>;
    });
    return (
        <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a className="navbar-brand" href="#">Config service</a>
            <ul className="navbar-nav bd-navbar-nav flex-row">
                <li className="nav-item">
                    <div className="dropdown">
                        <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Applications
                        </button>
                        <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            {applicationsHtml}
                        </div>
                    </div>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/web/meta">Meta</Link>
                </li>
            </ul>
        </nav>
    );
};