ARG BUILD_IMAGE=maven:3.6.3-jdk-11
ARG RUNTIME_IMAGE=openjdk:11-jdk-slim

# Docker is pulling all maven dependencies
FROM ${BUILD_IMAGE} as dependencies

WORKDIR /build
COPY pom.xml /build/

RUN mvn -B dependency:go-offline

# Docker is building spring boot app using maven
FROM dependencies as build

WORKDIR /build
COPY src /build/src

RUN mvn -B clean package

# Docker is running a java process to run a service built in previous stage
FROM ${RUNTIME_IMAGE}

WORKDIR /app
COPY --from=build /build/target/dw.jar /app/dw.jar

CMD ["sh", "-c", "java -jar /app/dw.jar"]