# Learning Spring
- Test spring for devops projects

## Setup Local
- First Setup mysql or run mysql in docker

### Create a Mysql Container 
```bash
$ docker run -e MYSQL_ROOT_PASSWORD=prodroot -e MYSQL_DATABASE=learning_spring -p 3306:3306 mysql
```

### Run the App Locally

```bash
$ mvn clean && mvn install & mvn spring-boot:run -Dspring.profiles.active=prod
```


## Setup Docker

### Start Spring Container
- Pull Container for docker hub
```bash
$ docker pull yashsahani/spring-learning
```

- Run Container

```bash
$ docker run -p 8080:8080 --name testSpringApp yashsahani/spring-learning 
```

## Run Via Docker-Compose 
```bash
$ docker-compose up
```
