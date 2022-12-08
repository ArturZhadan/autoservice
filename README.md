#  :car: ***AutoService*** :car:
___
### :pushpin: ***Project description*** :pushpin:
***Web-application that supports CRUD operations with workers, cars, orders, owners, products and proposals***
___
### :bookmark: ***Features:*** :bookmark:
+ :notebook: ***create/update a worker***
+ :notebook: ***create/update a car***
+ :notebook: ***create/update an order***
+ :notebook: ***create/update an owner***
+ :notebook: ***create/update a product***
+ :notebook: ***create/update a proposal***
+ :notebook: ***read orders list by worker id***
+ :notebook: ***read worker salary by id***
+ :notebook: ***add product to products list by order id***
+ :notebook: ***read order price by id***
+ :notebook: ***read orders list by owner id***
___
### :open_file_folder: ***Structure:*** :open_file_folder:
+ ***Controller - accepts requests from the client, passes them to the service layer***
+ ***Service - accepts requests from the controller, passes them to the DAO layer and performs all business logic***
+ ***DAO - accepts requests from the service, passes them to the DB***
___
### :page_with_curl: ***Technologies:*** :page_with_curl:
+ ***[Maven](https://maven.apache.org/download.cgi)***
+ ***[PostgreSQL](https://www.postgresql.org/download/)***
+ ***SpringBoot 2.7.5***
+ ***[Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)***
+ ***Liquibase***
+ ***Swagger***
___
### :question: ***How to run this project: :question:***
- [x] ***Clone the project***
- [x] ***Write your properties to application.properties file***
- [x] ***Run the project***
___
###  ***Example GET requests***
```java
        http://localhost:8090/workers
        http://localhost:8090/workers/1
        http://localhost:8090/workers/1/salary
        http://localhost:8090/cars
        http://localhost:8090/cars/1
        http://localhost:8090/products
        http://localhost:8090/products/1
        http://localhost:8090/proposals
        http://localhost:8090/proposals/1
        http://localhost:8090/orders
        http://localhost:8090/orders/1
        http://localhost:8090/orders/1/add-product
        http://localhost:8090/orders/1/price
        http://localhost:8090/owners
        http://localhost:8090/owners/1
```
