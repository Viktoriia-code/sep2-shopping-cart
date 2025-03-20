FROM maven:latest

LABEL authors="viki-kone"

WORKDIR /app

COPY  pom.xml /app

COPY . /app

RUN mvn package

CMD ["java", "-jar", "target/shoppingcart.jar"]