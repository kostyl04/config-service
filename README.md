# Config-service
Application to configure your applications properties in runtime. Your application will receive property update by jms and will update its value in runtime.
Contains:
## Config service rest:  Service to manage parameters/messages for different applications
## Config provider: Library to set up required properties, init them, and process updates.
## Config provider autoconfiguration: Spring boot starter to init config provider beans.
