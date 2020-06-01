import View from "./headerView";
import configServiceClient from "../../client/client"

const React = require('react');

export default class Header extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            applications: []
        };

    }

    componentDidMount() {
        let that = this;
        configServiceClient.getApplicationNames().then((apps) => {
            that.setState({
                applications: apps
            });
        });
    }

    render() {
        return (
            <View applications={this.state.applications}/>
        )
    }
}