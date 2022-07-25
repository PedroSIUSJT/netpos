<h1>Projeto Netpos</h1>

* Este projeto é uma API REST escrita em Spring Boot com Java utilizando Maven com um banco de dados em memória (H2DataBase)

* Baixe o projeto acesse a pasta via terminal e rode o comando "mvn spring-boot:run" ( É necessário ter o maven instalado )
 
### endpoints - User

GET  http://localhost:8080/user/{id}  - Busca o usuário por id

POST http://localhost:8080/user   - Cadasta um usuário

### endpoints - Product

GET  http://localhost:8080/product - Busca os produtos

GET  http://localhost:8080/product/{code} - Busca um produto por código

POST http://localhost:8080/product  - Cadastra um novo produto

PUT  http://localhost:8080/product/{code} - Edita um produto

SWAGGER http://localhost:8080/index.html

H2 CONSOLE - http://localhost:8080/h2-console/


```json
{
    "name": "Seu nome",
    "email": "Seu@email.com",
    "password": "Senha"
}
```

Obs: O banco de dados por ser em memória terá a vida da aplicação, ou seja, as novas contas inseridas viverão
enquanto a aplicação estiver viva.
