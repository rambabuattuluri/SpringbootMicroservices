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
# Make port 9999 available to the world outside this container
EXPOSE 9999
# The application's jar file
ARG JAR_FILE=target/*.jar
# Add the application's jar to the container
ADD ${JAR_FILE} cart-auth-server.jar
# Run the jar file 
ENTRYPOINT ["java","-jar","/cart-auth-server.jar"]