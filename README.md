# Phone-validator

## How to use
1) build .jar file:
    ```bash
    ./gradlew build
    ```
   without tests
    ```bash
    ./gradlew build -x test
    ```

2) execute application with default database password and username:
    ```bash
     cd build/libs java -jar phone-validator-1.0.jar
    ``` 
3) execute application with custom database password and username:
    ```bash
     cd build/libs java -jar -Dspring.datasource.username=yourUserName -Dspring.datasource.password=yourPassword phone-validator-1.0.jar
    ```

## Run used docker compose

```bash
  docker-compose up
```

## Test report

To see test report go to
    ```bash
     cd build/reports/tests/test
    ``` 
and open index.html file