# J2EE-Family Blog Project

### General Description
* Frontend (JSP + Bootstrap4 + DataTable)
* Backend (Spring MVC 5 + GenericDao)
* Session attribute for user/privilege control 
* Full-control of admin-panel
* In PROD - AWS deployment, Tomcat 8.5.23 as container
* In DEV - Jetty can serve as the container (tomcat can be used as well)

### Environment
* JDK: 11
* IDE: IntelliJ

### GenericDAO package installation in Maven
```bash
$ cd [project_root]
$ mvn install:install-file -Dfile=genericdao-3.0.2.jar -DgroupId=org.genericdao -DartifactId=genericdao -Dversion=3.0.2 -Dpackaging=jar
```

### Instructions to run with Tomcat Server
```bash
Step-0: Download Tomcat v8.5 (eg. 8.5.23) or higher
Step-1: Hit Edit Configurations in IntelliJ
Step-2: Setup the Application Server
Step-3: Click the `Tomcat 8.5.23` in Run/Dug Configuration bar to run
visit: http://localhost:8080/familyblog or the URL configure such as _explored
```

### Instructions to run with Maven Jetty
```bash
$ cd [project_root]
$ mvn jetty:run
visit: http://localhost:8080/
```

### Style-check
in project root directory
```bash
$ mvn checkstyle:checkstyle
```

### Notes
* Code was initialized in May 2018
* Code was updated in March 2021
