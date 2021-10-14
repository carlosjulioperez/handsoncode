# Project User

## Source code:
`https://github.com/carlosjulioperez/handsoncode/tree/main/java/springBoot/users`

## Download the application
* Visit `https://downgit.github.io`
* Input the source code URL.
* Download as a zip file.

## Generating fatJar
$ ./gradlew uberJar

## Starting service
$ ./gradlew bootRun

## Executing test
$ ./gradlew test

## Executing specific test
$ ./gradlew :profile:testDebug --tests "*my_profile*"
$ ./gradlew :users:EmailValidationUnitTest --tests "*testUsingEmailValidator*"

## Packaging app
$ ./gradlew bootJar

## Running H2 Console
`http://localhost:8080/h2`

## Swagger API documentation:
`http://localhost:8080/swagger-ui/`
