
# Build Stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Runtime Stage
FROM amazoncorretto:17
ARG PROFILE=staging
ARG APP_VERSION=1.0.1

WORKDIR /app
COPY --from=build /build/target/book-network-*.jar /app/

EXPOSE 8088

ENV DB_URL=jdbc:postgresql://pg_sql_book:5432/borrow_books
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}
ENV EMAIL_HOST_NAME=hostname
ENV EMAIL_PASSWORD=password
ENV EMAIL_USERNAME=username
ENV EMAIL_PORT=port

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} book-network-${JAR_VERSION}.jar

