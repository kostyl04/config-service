// ui/Thing/index.js
const React = require('react');
import configServiceClient from "../../client/client"

export default class CreateMetaModel extends React.Component {
    constructor(props) {
        super(props);
        this.onMetaCreate = props.onMetaCreate;
        this.updateName = this.updateName.bind(this);
        this.updateKeyDelimiter = this.updateKeyDelimiter.bind(this);
        this.addNewField = this.addNewField.bind(this);
        this.removeField = this.removeField.bind(this);
        this.updateField = this.updateField.bind(this);
        this.saveMeta = this.saveMeta.bind(this);
        this.initMeta();
    };

    initMeta() {
        this.state = {
            meta: {
                name: "",
                keyDelimiter: "",
                fields: []
            },
            hasError: false,
            error: ""
        };
    }

    updateName(event) {
        this.state.meta.name = event.target.value;
        this.setState(this.state);
    }

    updateKeyDelimiter(event) {
        this.state.meta.keyDelimiter = event.target.value;
        this.setState(this.state);
    }

    addNewField() {
        this.state.meta.fields.push({
            name: "",
            key: false,
            type: "STRING",
            nullable: true,
            immutable: false
        });
        this.setState(this.state);
    }

    removeField(event) {
        let target = $(event.target);
        let index = target.attr("field-index");
        this.state.meta.fields.splice(index, 1);
        this.setState(this.state);
    }

    updateField(event) {
        let input = $(event.target);
        this.state.meta.fields[input.attr("field-index")][input.attr("field-property")] = input.val();
    }

    saveMeta(event) {
        let that = this;
        configServiceClient.saveMeta(this.state.meta)
            .then(result => {
                if (result.code) {
                    that.state.hasError = true;
                    that.state.error = result.message;
                    that.setState(that.state);
                } else {
                    that.onMetaCreate(that.state.meta.name);
                    that.initMeta();
                    $("#createMetaModal").modal("hide");
                    that.setState(that.state);
                }
            });
    }

    render() {
        let htmlFields = this.metaFieldsToHtml();
        let error = this.state.hasError ? <div className="col-12 bg-danger"><h2>{this.state.error}</h2></div> : "";
        return (
            <div className="modal fade" id="createMetaModal" tabIndex="-1" role="dialog"
                 aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div className="modal-dialog modal-lg" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLabel">Create meta</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            {error}
                            <form>
                                <div className="row form-group">
                                    <div className="col">
                                        <input type="text" className="form-control" placeholder="Name"
                                               onChange={this.updateName} value={this.state.meta.name}/>
                                    </div>
                                    <div className="col">
                                        <input type="text" className="form-control" placeholder="Key delimiter"
                                               onChange={this.updateKeyDelimiter} value={this.state.meta.keyDelimiter}/>
                                    </div>
                                </div>
                                <div className="row form-group">
                                    <div className="col">
                                        Name
                                    </div>
                                    <div className="col">
                                        Key
                                    </div>
                                    <div className="col">
                                        Type
                                    </div>
                                    <div className="col">
                                        Nullable
                                    </div>
                                    <div className="col">
                                        Immutable
                                    </div>
                                    <div className="col">
                                    </div>
                                </div>
                                {htmlFields}
                                <div className="row form-group">
                                    <div className="col">
                                        <div className="form-control btn btn-success" onClick={this.addNewField}>Add
                                            field
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-primary" onClick={this.saveMeta}>Save</button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    metaFieldsToHtml() {
        let that = this;
        return this.state.meta.fields.map((field, index) => {
            return <div className="row form-group" key={index}>
                <div className="col">
                    <input type="text" className="form-control" placeholder="Name" field-index={index}
                           field-property="name" onChange={that.updateField}/>
                </div>
                <div className="col">
                    <select id="keySelect" field-index={index} field-property="key" className="custom-select"
                            onChange={that.updateField} defaultValue={false}>
                        <option value="false">false</option>
                        <option value="true">true</option>
                    </select>
                </div>
                <div className="col">
                    <select id="typeSelect" field-index={index} field-property="type" className="custom-select"
                            onChange={that.updateField} defaultValue={"STRING"}>
                        <option value="STRING">STRING</option>
                        <option value="BOOLEAN">BOOLEAN</option>
                        <option value="FLOAT">FLOAT</option>
                        <option value="LIST">LIST</option>
                        <option value="LONG">LONG</option>
                        <option value="MAP">MAP</option>
                    </select>
                </div>
                <div className="col">
                    <select id="keySelect" field-index={index} field-property="nullable" className="custom-select"
                            onChange={that.updateField} defaultValue={true}>
                        <option value="false">false</option>
                        <option value="true">true</option>
                    </select>
                </div>
                <div className="col">
                    <select id="keySelect" field-index={index} field-property="immutable" className="custom-select"
                            onChange={that.updateField} defaultValue={false}>
                        <option value="false">false</option>
                        <option value="true">true</option>
                    </select>
                </div>
                <div className="col">
                    <i className="fas fa-ban" onClick={that.removeField} field-index={index}/>
                </div>
            </div>
        });
    }
}