FROM openjdk:17

COPY build/libs/phone-validator-1.0.jar phone-validator.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "phone-validator.jar"]
