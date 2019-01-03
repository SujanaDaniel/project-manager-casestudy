FROM openjdk:8
ADD /project-manager-service-0.0.1-SNAPSHOT.jar project-manager-service.jar
EXPOSE 8088
RUN bash -c 'touch /project-manager-service.jar'
ENTRYPOINT ["java","-jar","project-manager-service.jar"]