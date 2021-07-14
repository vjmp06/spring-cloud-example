# Microservices Spring Cloud Example

Esse exemplo possui 5 aplicações java em gradlew 2 delas microsserviços que se comunicam com o Elastic Search (product-catalog) e Redis (shopping-cart), as outras 3 fazem parte da estrutura de microsserviços ConfigServer (config-server), Service Registry/Discovery (service-discovery) e Gateway (gateway)

ConfigServer

Responsável por centralizar e fornecer arquivos de configuração aos microsserviços.

Registry/Discovery

Responsável por centralizar e registrar as informações de acesso aos microsserviços, quem quiser chamar um microserviço passa por aqui.

Gateway

Responsável por balancear e centralizar as chamadas aos microsserviços podendo também ser responsável pela autenticação de quem irá consumi-los, aplicação web ou mobile por exemplo.


### Pré requisitos

1. Java 11
2. Docker
3. Docker Composer
4. IDE
5. Insomnia/Postman

### Iniciando o back-end

1. docker-compose up -d (Ira executar o arquivo docker-compose.yml, baixando e subindo as instancias do Elastic Search e Redis)
2. Importar os projetos para uma IDE (Eclipse| Intellij | Visual Studio Code Com extensões para Java)
3. Iniciar os projetos na seguinte ordem:
  
```
config-server
service-discovery
gateway
product-catalog | shopping-cart
```


### Config Server

1. Use o Insomnia/Postman
```
GET http://localhost:8888/product-catalog/default

{
  "name": "product-catalog",
  "profiles": [
    "default"
  ],
  "label": null,
  "version": "bbdb76f37b81ff4fa3a696836c89f8844fc272f1",
  "state": null,
  "propertySources": [
    {
      "name": "https://github.com/vjmp06/dio-experts-config.git/product-catalog.yml",
      "source": {
        "server.port": 8081,
        "eureka.instance.hostname": "localhost",
        "eureka.instance.port": 9000,
        "eureka.client.registerWithEureka": true,
        "eureka.client.fetchRegistry": false,
        "eureka.client.serviceUrl.defaultZone": "http://${eureka.instance.hostname}:${eureka.instance.port}/eureka/",
        "eureka.client.server.wait-time-in-ms-when-sync-empty": 3000
      }
    }
  ]
}

GET http://localhost:8888/shopping-cart/default

{
  "name": "shopping-cart",
  "profiles": [
    "default"
  ],
  "label": null,
  "version": "bbdb76f37b81ff4fa3a696836c89f8844fc272f1",
  "state": null,
  "propertySources": [
    {
      "name": "https://github.com/vjmp06/dio-experts-config.git/shopping-cart.yml",
      "source": {
        "server.port": 8082,
        "eureka.instance.hostname": "localhost",
        "eureka.instance.port": 9000,
        "eureka.client.registerWithEureka": true,
        "eureka.client.fetchRegistry": false,
        "eureka.client.serviceUrl.defaultZone": "http://${eureka.instance.hostname}:${eureka.instance.port}/eureka/",
        "eureka.client.server.wait-time-in-ms-when-sync-empty": 3000
      }
    }
  ]
}

```

### Service Registry/Discovery

1. No navegador acessar o endereço http://localhost:9000

![eureka-service](https://user-images.githubusercontent.com/17283891/125543548-510c5523-8a99-4366-ba7b-bd5db3704538.png)

## Gateway

```
Cadastrar Produto

POST http://localhost:8080/product

Body:

{
  "id": 1,
  "name": "Teste",
  "amount": 1
}

Recuperar Produto

GET http://localhost:8080/product/1

Resposta:

{
  "id": 1,
  "name": "Teste",
  "amount": 1
}


Adicionar Produto ao Carrinho

POST http://localhost:8080/cart/123

Body:

{
	"productId": 1,
	"amount": 1
}

Recuperar Carrinho

GET http://localhost:8080/cart/123

Resposta:

{
  "id": 123,
  "items": [
    {
      "productId": 1,
      "amount": 1
    }
  ]
}

Apagar Carrinho

DELETE http://localhost:8080/cart/123

```

## Product Catalog

```
Cadastrar Produto

POST http://localhost:8081/product

Body:

{
  "id": 1,
  "name": "Teste",
  "amount": 1
}

Recuperar Produto

GET http://localhost:8081/product/1

Resposta:

{
  "id": 1,
  "name": "Teste",
  "amount": 1
}

```
## Shopping Cart

```
Adicionar Produto ao Carrinho

POST http://localhost:8082/cart/123

Body:

{
	"productId": 1,
	"amount": 1
}

Recuperar Carrinho

GET http://localhost:8082/cart/123

Resposta:

{
  "id": 123,
  "items": [
    {
      "productId": 1,
      "amount": 1
    }
  ]
}

Apagar Carrinho

DELETE http://localhost:8082/cart/123

```

Observações

Nessa primeira versão o objetivo era utilizar a estrutura do Spring Cloud para consumir microserviços, os projetos utilizados como exemplo podem e devem ser melhorados!


Referencias

https://seprosp.digitalinnovation.one/course/micro-servicos-em-spring-cloud-com-java/learning/00467dc2-9bec-4414-9943-8b0ca56b6c7f/?back=/browse
