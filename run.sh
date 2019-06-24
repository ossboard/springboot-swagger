gradle build -x test
java -jar build/libs/restapi-1.0.war --server.port=8081 --spring.profiles.active=default --server.servlet.context-path=/demo

