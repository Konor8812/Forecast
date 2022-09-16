FROM bellsoft/liberica-openjdk-alpine as build
ENV PROJECT_ROOT usr/src/app
WORKDIR $PROJECT_ROOT
COPY target/forecast-core.jar forecast-core.jar
CMD java -jar forecast-core.jar