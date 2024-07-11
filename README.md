# Java CRUD Backend Application predicting EURO 2024 winner
CRUD application created with Spring Boot and PostgreSQL.

# Introduction
The aim of this project is to estimate the winner of the EURO 2024 with a given probability. A logistic regression model was build based on team's previous major tournament results and its position in FIFA Ranking. Then every match outcome is predicted by estimated model. The whole tournament is simulated n times. Based on that the probability of winning is calculated.

# Technologies
- Java 20
- Spring Boot 3.1.4
- JUnit 5
- Hibernate 6.2.6
- Weka 3.8.5
- PostgreSQL 16rc1
- pg Admin 4, version 7.6
- Python 3.11.6
- Requests 2.31.0
- BeautifulSoup 4.12.3
- Postman 10.24.16
- IntelliJ IDEA Community Edition 2023.2.2
- PyCharm Edu 2022.2.2

## Features
- Web scrapper collects match statistics and FIFA Ranking data and sends it to database.
- CRUD operations can be performed on every entity.
- Logistic Regression model is trained to predict outcome of the match. At this moment model is able to achive accuracy above 72.5%.
- The number of simulations can be freely determined and it will affect outcomes precision.
- Unit tests ensure that the application works correctly.

# Examples
- http://localhost:8080/api/v1/match/update/347 is going to update match with id 347

- http://localhost:8080/api/v1/regression/predict/euro2024/1000 is going to train logistic regression model, simulate EURO 2024 1000 times and save results to database. It returned following probabilities: France: 30.8%, Portugal: 27.6%, England: 15.3%, Holand: 7.4%, Belgium: 6%, Croatia: 5.1%, Switzerland 2.6%, Spain: 2.5%, Italy 1.3%, Germany 0.7%, Denmark: 0.4%, Poland: 0.2%, Slovakia: 0.1%.

# Database schema:
![database_schema](https://github.com/Krzysztof-Kobylkiewicz/probability/assets/175056724/aac2469e-ef9f-4c0b-9151-43cf53a05052)

# Project status
Simulation of EURO 2024 is fully implemented. In the future project can be :

- extended with possibility of simulating other major tournaments.
- updated with better version of model. Currently model uses pravious match statistics and FIFA Ranking Data but can be extended by new variables or by weighting tournament statistics from the most recent to the oldest.
- updated with funcionality that allows users to create their own tournaments and predict its winners.
