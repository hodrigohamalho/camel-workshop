= Red Hat Camel 3 Workshop application

This is a Red Hat Camel 3 project (with Quarkus framework) to use with Red Hat workshop. 

All the labs instructions https://github.com/GuilhermeCamposo/workshop_camel-springboot/tree/main/walkthroughs[are here].


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```

## Running the application in Openshift

You can run your application in dev mode that enables live coding using:
```shell script
mvn clean package -DskipTests -Dopenshift
```

There is some properties related to Openshift deployment in the application-prod.properties file
