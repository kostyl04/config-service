'use strict';

class ConfigServiceClient {

     async getApplicationNames() {
         let response = await fetch("/api/applications",{
             headers: {
                 'Content-Type': 'application/json'
             }
         });
         let json = await response.json();
         console.log(json[0]);
         return Array.from(json);
    }
}

export const configServiceClient = new ConfigServiceClient();