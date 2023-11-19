# Departmental Store Management System

- This is Departmental Store Management System project, which is taken as part of SSE-Survey.
- This project runs using the Backend Framework -> Spring Boot framework, Template Engine -> Thymeleaf, Database -> Mongo DB, Security -> Spring Boot Security.

### Build and running the project

- Install Mongo DB community server and Mongo DB compass
- Install Java 1.8
- Install Intellij IDEA IDE
- Open Mongo DB compass and click on connect as shown below
![Mongo DB Compass](readme_files/compass.png)
- Click on create Data base
![Create DB](readme_files/db_create.png)
![Create DB 1](readme_files/db_create1.png)
- Click on import Json (Select the file that is in "pre_docs" folder)
![Create DB 1](readme_files/import_collection.png)
- Clone the project
- Open the project by clicking on the "build.gradle" file
- Once after the build, click on the Run Configuration which is automatically created by Intellij IDEA.Run Configuration name - DsmsApplication

### Accessible URL's

- The application will run on the port 7075
- Login page - http://127.0.0.1:7075/login (admin1@gmail.com / admin) (user3@gmail.com / user)
- Registration Page - http://127.0.0.1:7075/register (Register user by giving username and password)