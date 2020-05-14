# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine
# Add Maintainer Info
LABEL maintainer="rambabuattuluri"
#Create Group and UserID 
RUN addgroup -S spring && adduser -S spring -G spring
#RUN AS USERID
USER spring:spring
# Add a volume pointing to /opt/app
VOLUME /opt/app
# Make port 9200 available to the world outside this container
EXPOSE 9200
# The application's jar file
ARG JAR_FILE=target/*.jar
# Add the application's jar to the container
ADD ${JAR_FILE} product-service.jar
# Run the jar file 
ENTRYPOINT ["java","-jar","/product-service.jar"]