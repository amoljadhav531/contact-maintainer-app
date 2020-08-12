# contact-maintainer-app
This application helps to maintain and modify the contact information persons

*** PREREQUISITE :

- JDK 1.8 and above
- Eclipse OR maven
- MySql server With Workbench(for easiness)
- Have database schema created with name "CONTACT_REPO" (you can create with other name as well but need to update application.properties file mentioned below)
- SonarQube if need to check sonar report(Optional)
- Git to clone the repo (Optional - you can directly download a project in zip)


*** APPLICATION RUN GUIDE :

- Clone the project from repository : https://github.com/amoljadhav531/contact-maintainer-app
- Build the project with command "mvn clean install"
- Change the application.properties file with below change:
     1. spring.datasource.url= add you mysql datasource url
     2. spring.datasource.username= add you MySql username
     3. spring.datasource.password= add you MySql password
- if have a Eclipse run the class ContactMaintainerAppApplication with right click -> run as -> javaApplication
- if not have a Eclipse you can run jar file from target folder with command "java -jar contact-maintainer-app-0.0.1-SNAPSHOT.jar"
- Then check the applicayion running on browser with URL "http://localhost:9090/contact-maintainer/"
- it will show you available contacts in database
- upper side we have hyper link "Create New Contact", so can add contact and also after adding contact we have EDIT and DELETE batton to do this operation

