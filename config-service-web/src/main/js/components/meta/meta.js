const React = require('react');
const ReactDOM = require('react-dom');
import configServiceClient from "../../client/client"
import MetaNavView from "./metaNav";
import MetaView from "./metaView";
import CreateMetaModal from "./metaCreateModal"
import * as QueryString from "query-string"

export default class Meta extends React.Component {

    constructor(props) {
        super(props);
        this.onMetaChange = this.onMetaChange.bind(this);
        this.onMetaCreate = this.onMetaCreate.bind(this);
        this.state = {
            metas: [],
            currentMeta: {}
        };
        const params = QueryString.parse(this.props.location.search);
    }

    onMetaChange(metaName) {
        let newCurrentMeta = this.state.metas.find(meta => {
            return meta.name === metaName;
        });
        this.setState({currentMeta: newCurrentMeta});
    }

    onMetaCreate(metaName) {
        this.reloadMetas(metaName);
    }

    componentDidMount() {
        this.reloadMetas();
    }

    reloadMetas(selectedMetaName) {
        let that = this;
        configServiceClient.getMetas().then((metas) => {
            let currentMeta;
            if (selectedMetaName) {
                currentMeta = metas.find(meta => meta.name === selectedMetaName);
            } else {
                currentMeta = metas[0];
            }
            that.setState({
                metas: metas,
                currentMeta: currentMeta
            });
            $("#meta-nav-select").selectpicker({liveSearch: true});
            $('#meta-nav-select').selectpicker('refresh');
            $("#meta-nav-select").selectpicker("val", currentMeta.name);
        });
    }

    render() {
        return (
            <div className="row">
                <div className="col-sm-3">
                    <MetaNavView currentMeta={this.state.currentMeta} metas={this.state.metas}
                                 onMetaChange={this.onMetaChange}/>
                </div>
                <div className="col-sm-1"></div>
                <div className="col-sm-8">
                    <MetaView meta={this.state.currentMeta}/>
                </div>
                <CreateMetaModal onMetaCreate={this.onMetaCreate}/>
            </div>
        )
    }
}