FROM openjdk:11.0-jre-buster

# Copia o jar que está no target para a pasta do app e executa
COPY ./backend/web/target/web-1.0.jar /usr/app/
WORKDIR /usr/app
EXPOSE 80
ENTRYPOINT ["java", "-jar", "web-1.0.jar"]