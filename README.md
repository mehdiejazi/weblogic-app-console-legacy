# WebLogic App Console Legacy

This folder contains the legacy Java EE version of a generic WebLogic deployment and operations console.

## Purpose

- Upload and deploy a `.war` artifact to a WebLogic target
- Restart the deployed application
- Start or stop the WebLogic runtime
- Monitor application and server status
- Manage users and page access in the legacy console

## Project Layout

- `src` and `web`: legacy JSP/Servlet/Spring MVC application

## Before Publishing or Running

- Replace all placeholder database, SSH, and WebLogic values with your own environment settings
- Review shell scripts under `web/WEB-INF/shcommands`
- Exclude generated archives and build outputs from the repository
- `web/WEB-INF/web-param.xml` can now be committed with plain-text placeholders or encrypted values

## Notes

This version is prepared for public source control:

- Domain-specific labels were removed from code and UI text
- Hard-coded database credentials and infrastructure addresses were replaced with placeholders
- Generic context paths, artifact names, working directories, and activity log names were introduced for reuse
