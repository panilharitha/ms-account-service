## ms-account-service
`This micro service can you for retrive account details and transaction details.`

    This microservice contain 2 apis 
        -  View account list
        -  View account transactions
[This is url for api documentation](http://localhost:8080/swagger-ui/index.html)

Following technologies has been used for develop this microservice
- Java11
- SpringBoot
- Mysql
- Flyway
- JUnit
- Gradle
- Lombok
- Springfox



### Setup Local Environment

#### To bootstrap the local dependencies. Use below gradle task

`./gradelw startLocalEnvironment` or `./gradlew startLE`


#### To shutdown the local dependencies. Use below gradle task

`./gradlew stopLocalEnvironment` or `./gradlew stopLE`

#### To run the microservice. Use below gradle task

`./gradlew bootrun`



#### To run the test cases. Use below gradle task

`./gradlew test`

### Dependencies

`Mysql DB will start in localhost with port 3306`
