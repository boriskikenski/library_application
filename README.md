This application was developed using Spring Boot and Java with layered architecture.\
The application runs on port 8080 and the following urls are available:\
On http://localhost:8080/api/** are available all the end points. \
The database is accessed on: http://localhost:8080/h2 \
The GraphQL end points are available on:
http://localhost:8080/graphiql \
I used H2 in memory database which means that the data is stored in memory and is not persisted on disk.\
The application has integration with log4j2 logger, which has scheduled running.
