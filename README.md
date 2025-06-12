# Employee test app
Just a simple CRUD REST app in java spring

There are two tables, Employees and Teams.
If the DB is empty, you can add them without tieing them together and once you have some data you can update them to be connected (Employee1 -> Team 1; Team 1's team lead -> Employee1 etc.)

## How to start
Just run the `EmployeeTestAppApplication`

## Docs
Once the app is started you can access the API docs via http://localhost:8080/swagger-ui/index.html#/

## DB
I've used H2 for simplicity and you can access the pannel via http://localhost:8080/h2

##
There is a Postman collection included to test all the endpoints within the project named `REST API basics- CRUD`
