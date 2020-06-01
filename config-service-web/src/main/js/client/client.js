'use strict';

class ConfigServiceClient {

    async getApplicationNames() {
        let response = await fetch("/api/applications", {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        let json = await response.json();
        return Array.from(json);
    }

    async getMetas() {
        console.log("called");
        let response = await fetch("/api/meta", {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        let json = await response.json();
        return Array.from(json);
    }

    async saveMeta(meta) {
        if (!meta.keyDelimiter || meta.keyDelimiter.length === 0) {
            meta.keyDelimiter = null;
        }
        let response = await fetch("/api/meta", {
            method: 'POST', // *GET, POST, PUT, DELETE, etc.
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(meta) // body data type must match "Content-Type" header
        });
        return await response.json();
    }
}

export default new ConfigServiceClient();