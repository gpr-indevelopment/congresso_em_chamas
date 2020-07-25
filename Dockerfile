FROM java:8-jdk-alpine

# Copia o jar que está no target para a pasta do app e executa
COPY ./backend/target/CongressoEmChamas-0.0.2-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 80
ENTRYPOINT ["java", "-jar", "CongressoEmChamas-0.0.2-SNAPSHOT.jar"]