FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY App.java .
RUN javac App.java
EXPOSE 8080
ENTRYPOINT ["java", "App"]