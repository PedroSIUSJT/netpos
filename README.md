<h1>Projeto Netpos</h1>

* Este projeto é uma API REST escrita em Spring Boot com Java utilizando Maven com um banco de dados em memória (H2DataBase)

* Baixe o projeto acesse a pasta via terminal e rode o comando "mvn spring-boot:run" ( É necessário ter o maven instalado )
 
### endpoints

GET   http://localhost:8080/user   Busca os usuários

POST   http://localhost:8080/user   Cadasta um usuário

GET   http://localhost:8080/product   Busca os produtos

POST   http://localhost:8080/product   Cadastra um novo produto

SWAGGER http://localhost:8080/index.html


```json
{
    "nome": "Seu nome"
}
```

Obs: O banco de dados por ser em memória terá a vida da aplicação, ou seja, as novas contas inseridas viverão
enquanto a aplicação estiver viva.
