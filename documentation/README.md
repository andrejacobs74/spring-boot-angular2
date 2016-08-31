**Micro service with Spring Boot and Angular2**

For installation information of angular2 and required tools see documentation on

https://angular.io/docs/ts/latest/quickstart.html

**Description**

This application provide a small web client based an spring boot with and angular2 version 2.0.0-rc.5. The backend based on 
spring security with usage of Java Web Token. The client consists a login and a home page which is accessable after
successful login. This application shows a possible implementation of a ui micro service.

**Install required libs**

    npm install

**Compile Typescript**

    Compile typescript: npm run tsc -w

**Build Application**

    Copy web app to /resources/static with gulp
    
    Run gulp from command line: gulp

**Run application**

Url of the application

    localhost:8080/formLogin

Login

    User: josh
    Password: test
    
    