import {View} from "./headerView";

const React = require('react');

export default class Header extends React.Component {

    constructor(props) {
        super(props);
        this.onApplicationChange = this.onApplicationChange.bind(this);
    }

    componentDidMount() {

    }

    onApplicationChange(appName) {
        console.log(appName)
        this.props.onApplicationChange(appName);
    }

    render() {
        return (
            <View onApplicationChange={this.onApplicationChange} applications={this.props.applications}/>
        )
    }
}