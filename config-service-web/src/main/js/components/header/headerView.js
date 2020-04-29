// ui/Thing/index.js
const React = require('react');

export const View = (props) => {
    const {
       applications,
        onApplicationChange
    } = props;
    const onChange=(event)=>{
        onApplicationChange(event.target.getAttribute("value"));
    };
    let applicationsHtml = applications.map(app=>{
        return <a className="dropdown-item" onClick={onChange} value={app} key={app} href="#">{app}</a>;
    });
    return (
        <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a className="navbar-brand" href="#">Config service</a>
            <div className="dropdown">
                <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Applications
                </button>
                <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    {applicationsHtml}
                </div>
            </div>
        </nav>
    );
};