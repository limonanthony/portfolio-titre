FROM openjdk:24-bullseye AS builder
LABEL authors="anthonylimon"

RUN apt-get update && apt-get install -y findutils


WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew /app/
COPY gradle /app/gradle

COPY src /app/src

RUN chmod +x ./gradlew

RUN ./gradlew installDist

FROM openjdk:24-bullseye AS runner
LABEL authors="anthonylimon"

WORKDIR /app

COPY --from=builder /app/build/install/api /app/

EXPOSE ${API_PORT}

ENTRYPOINT ["./bin/api"]