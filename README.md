Project Manager Case Study Build Notes:

Application Structure: 
Parent maven project(project-manager-aggregator) with two dependent modules - For building and packaging both service and UI into a jar.
project-manager-service - Maven Spring boot project for exposing rest endpoints + MySql.
project-manager-ui - Angular CLI for building UI.

Git Repository: https://github.com/SujanaDaniel/project-manager-casestudy.git

Please refer the "docs" folder for the details mentioned below:
folder "application_screenshots" : contains the application screenshots of all screens.
folder "mysql" : contains the DDL scripts.
folder "junit-eclemma_coverage" : contains the junit test case files and a screenshot of the eclemma code coverage report.
folder "jmeter" : contains the screenshots of the jmeter execution for the end-points.
folder "docker" : contains the commands and screenshots of the docker image creation and execution.
folder "jenkins" : contains the screenshots of the jenkins pipeline creation and execution. also contains build report
projectmanager-server - contains the REST end-points of the application
projectmanager-web - contains the latest angular cli code for application
Repo: 

Commands for final build:
Maven Build:
clean install -e	[run the command for project-manager-service project which will build UI and service project and create the final jar with required resources] package docker:build	[run the command for project-manager-service project which will copy the jar from target and create image in the remote docker container]

To run the created image and validate the image:
Connect to the remote docker machine To check whether the image is created in docker container dockerx image ls To run the created image in docker dockerx run -p 8085:8089 463657-project-manager:latest To check whether the image is running in docker[open new cmd prompt and run the cmd] dockerx ps To validate whether the application is working fine using curl command i) connect to bash shell in the container. [take container id of the image created from dockers ps] dockerx exec -it [CONTAINER_ID] bash ii) check whether application is working [it will return custom techincal error from the service exposed since mysql db is not available in docker] curl http://localhost:8085/project-manager/getTaskInfo

Jenkins: 
Make sure Jenkins is installed and running Configure Maven and JDK in jenkins with name maven3 and jdk1.8 
Create Jenkins project with Pipeline option and configure the below information 
i) Github repository with credentials 
ii) Branch to build: */master 
iii) Path to Jenkinsfile

Commands for local development:
Make sure mysql is running in local machine
spring boot in project-manager-server folder: spring-boot:run
angular ui in project-manager-web: npm install -> npm start

Note:
The port number of the service is configurable in the angular project (environment.ts file) 