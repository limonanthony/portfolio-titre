FROM openjdk:20-bullseye AS run-stage

WORKDIR /app

COPY . .

RUN rm .env

RUN chmod +x ./gradlew

ENV API_PORT=4242

EXPOSE ${API_PORT}
CMD ["./gradlew", "runWithEnv"]