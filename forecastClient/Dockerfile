FROM bellsoft/liberica-openjdk-alpine as build
ENV PROJECT_ROOT usr/src/app
WORKDIR $PROJECT_ROOT
COPY target/forecast-client.jar forecast-client.jar
CMD java -jar forecast-client.jar