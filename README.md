# J2EE-Family Blog Project

### General Description
* Frontend (JSP + Bootstrap4 + DataTable)
* Backend (J2EE + GenericDao)
* Session attribute for user/privilege control
* In PROD - AWS deployment, Tomcat 8.5.23 as container
* In DEV - Jetty can serve as the container (tomcat can be used as well)

### Environment
* JDK version: 11
* IDE: IntelliJ
* MySQL Server version: 5+

### Instruction for GenericDAO package installation in Maven
Download `genericdao-3.0.2.jar` from `http://www.genericdao.org/GenericDAO/` (created by Prof. Jeff Eppinge)
or
`http://www.genericdao.org/GenericDAO/genericdao-3.0.2.jar`
```bash
Move genericdao-3.0.2.jar file to [project_root]
$ cd [project_root]
$ mvn install:install-file -Dfile=genericdao-3.0.2.jar -DgroupId=org.genericdao -DartifactId=genericdao -Dversion=3.0.2 -Dpackaging=jar
You can read more about GenericDAO here: http://www.genericdao.org/GenericDAO/release-notes.html
```

### MySQL database creation
* Here I created an anonymous user in my MySQL Server: (username/password= ''/'')
* You can put your mysql credentials in webapp/WEB-INF/web.xml's `jdbcUser` and `jdbcPassword` params
* Right now, the database name = `testc`, if you want to use a different name, please change it in webapp/WEB-INF/web.xml's `jdbcURL` param
```sql
CREATE DATABASE testc CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
```
Tables will be created automatically with GenericDao in the first deployment

### Instructions to run with Maven Jetty
* assume you already have Maven downloaded and configured correctly
```bash
$ cd [project_root]
$ mvn jetty:run
visit: http://localhost:8080/ and register a new user to try
```

### Instructions to run with Tomcat Server
```bash
Step-0: Download Tomcat v8.5 (eg. 8.5.23) or higher
Step-1: Hit Edit Configurations in IntelliJ
Step-2: Setup the Application Server
Step-3: Click the `Tomcat 8.5.23` in Run/Dug Configuration bar to run
visit: http://localhost:8080/familyblog
  or the URL configure such as `http://localhost:8080/j2ee-familyblog:war_explored` (check your tomcat run config)
```

### Admin and regular User group
* separated from each other
* default admin user: `admin`/`admin` as username and password at `http://localhost:8080/adminLogin.do`
* you can register a new user at `http://localhost:8080/register.do`

### Style-check
in project root directory
```bash
$ mvn checkstyle:checkstyle
```

### Notes
* GoogleMap apiKey is not shared, so you should put a valid one to display the map correctly
* Code was initialized in May 2018
* Code was updated in March 2021
