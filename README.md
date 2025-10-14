# This is an ecommerce website project
This project following the project idea from roadmap.sh site. Here is the project link: 
https://roadmap.sh/projects/scalable-ecommerce-platform

## Installation 
(You need to already have the Docker Desktop and Git in you device)

1. Open the Command line in your D disk
2. Clone the project using below command
   ```
   git clone https://github.com/kyBa-Le/Smarket-backend
   ```
3. After the project is cloned, run the commands below (make sure that you have opened your docker desktop first)
   ```
   cd Smarket-backend
   ```
   ```
   docker compose up --build
   ```
   Then, wait for all services to start.
4. After all services start, open a web browser and access to Service Registry at ```localhost:8761``` to see all the registered services
5. If you have seen all the services had been registered, you are now can start to test the application api.

## API documentation
All the public api follow the REST naming convention

### Product service ```/api/products```
| Method  | Endpoint                | Description                    |
|:--------|:------------------------|:-------------------------------|
| GET     | /                       | Get all the products           |

## Architecture Overview
- Service Discovery: Spring Eureka
- API Gateway: Spring Cloud Gateway
- Microservices: Auth, Product, Order, User.
- Database: MySQL, MongoDB
- Containerization: Docker Compose
- Event streaming: Kafka
