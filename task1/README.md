# 1 задание
### Простое вводное задание для демонстрации взаимодействия двех микросервисов между собой + бд

# Instruction for start:

### docker-compose up -d

## Example of endpoints for consumer:
1) GET http://localhost:8081/api/v1/client/products?max_price=1000&min_price=100&category=cars&page=0&size=2

2) GET http://localhost:8081/api/v1/client/products/5

3) GET http://localhost:8081/api/v1/client/products/searchingByName?name=Xiaomi

4) POST http://localhost:8081/api/v1/client/products {"name": "qwe", "description": "aaaaa", "price": 56}

5) PUT http://localhost:8081/api/v1/client/products/1 {"name": "qwe", "description": "vedro", "price": 5600}

6) DELETE http://localhost:8081/api/v1/client/products/1