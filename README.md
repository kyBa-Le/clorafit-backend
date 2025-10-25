# This is an ecommerce website project built with Micorservices Architecture
This project following the project challenge from roadmap.sh website. Here is the project link: 
https://roadmap.sh/projects/scalable-ecommerce-platform

## Installation 
(You need Docker Desktop and Git in you device)

1. Open the Terminal in the location that you want to store the project
2. Clone the project using the below command
   ```
   git clone https://github.com/kyBa-Le/Smarket-backend
   ```
3. Create a ```.env``` following the ```.env.example``` in the root directory and spead the environment variables
4. After the project is cloned, run the commands below (make sure that you have opened your docker desktop first)
   ```
   cd Smarket-backend
   ```
   ```
   docker compose up --build
   ```
   Then, wait for all services to start.
5. After all services start, open a web browser and access to Service Registry at ```localhost:8761``` to see all the registered services
6. If you have seen all the services had been registered, you are now can start to test the application api.

## API documentation
All the public api follow the REST naming convention

### Product service ```/api/products```
| Method  | Endpoint                | Description                    |
|:--------|:------------------------|:-------------------------------|
| GET     | /                       | Get all the products           |

## Architecture Overview
- Service Discovery: Spring Eureka
- API Gateway: Spring Cloud Gateway
- Microservices: Auth, Product, Order.
- Database: MySQL, MongoDB
- Containerization: Docker Compose
- Event streaming: Kafka
