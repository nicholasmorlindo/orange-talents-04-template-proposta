FROM openjdk:11
VOLUME /proposta
EXPOSE 8080
COPY ./target/proposta-0.0.1-SNAPSHOT.jar proposta.jar
ENTRYPOINT ["java","-jar","/proposta.jar"]