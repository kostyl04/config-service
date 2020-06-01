import Header from "./components/header/header";
import {Route, Switch} from "react-router";
import Meta from "./components/meta/meta";
import {Router as Router} from "react-router-dom";

const React = require('react');
const ReactDOM = require('react-dom');
const history = require("history").createBrowserHistory();

class App extends React.Component {

    constructor(props) {
        super(props);
        this.onApplicationChange = this.onApplicationChange.bind(this);
        this.state = {applications: []};
    }

    componentDidMount() {

    }

    onApplicationChange(appName) {
        console.log(appName);
    }

    render() {
        return (
            <Router history={history}>
                <Header/>
                <main className="bd-masthead" id="content" role="main">
                    <div className="container">
                        <Switch>
                            {/*<Route path="/web/meta">
                                <Meta location={this.props.location}/>
                            </Route>*/}
                            <Route path="/web/meta" render={props =>
                                <Meta key={props.location.search} {...props}/>
                            }/>
                        </Switch>
                    </div>
                </main>
            </Router>
        )
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('react')
)