import Header from "./components/header/header";
import {configServiceClient} from "./client/client"

const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.onApplicationChange = this.onApplicationChange.bind(this);
        this.state = {applications: []};
        let that = this;
        configServiceClient.getApplicationNames().then((apps) => {
            that.setState({applications: apps});
        });
    }

    componentDidMount() {

    }

    onApplicationChange(appName) {
        console.log(appName);
    }

    render() {
        console.log("watching1")
        return (
            <Header applications={this.state.applications} onApplicationChange={this.onApplicationChange}/>
        )
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('react')
)