# Sistema de Catálogo de Livros
<strong>Contexto:</strong>

- Desenvolvimento de uma API para gerenciar um sistema de catálogo de livros
em uma biblioteca, utilizando Java 17.
---
<strong>Objetivo:</strong>

- Criar uma aplicação Spring Boot que forneça endpoints para a realização de
  operações CRUD em um banco de dados de livros. As operações devem incluir validações
  específicas para os campos de dados dos livros.
---
<strong>Requisitos:</strong>

- Ter o Java 17 previamente instalado em sua maquina.
- Ter o docker previamente instalado em sua maquina.
---
<strong>Instalação:</strong>

1. Clone o repositorio: git clone https://github.com/GabrielDeMirandaMello/book-catalogy.git
2. Entre no projeto neste caminho "book-catalogy\src\main\resources"
3. Abra o terminar e execute o comando a seguir
4. Comando -> docker-compose up -d
5. Seu container mysql já estara criado
6. Volte para a pasta raiz do projeto, abra o cmd e execute este comando para rodar a aplicação
7. Comando -> mvn spring-boot:run
---
<strong>APIs:</strong>

- Post: /livros
  ```
  {
    "titulo":"A Arte da Guerra",
    "autor":"sun Tzu",
    "editora":"Companhia das Letras",
    "dataPublicacao":"2003-03-30"
  }
  ```
- Put: /livros/{id}
- Json
- Delete: /livros/{id}
- Get: /livros?termo={termo}
- Get: /livros/contagem
- Get: /livros/{id}

---
<strong>Tecnologias Usadas:</strong>

1. Java 17
2. Spring Boot 3.1.8
3. Spring Data JPA
4. Validation
5. Lombok
6. Spring Web
7. Mockito
8. JUnit5
9. Swagger
    
---
<strong>Documentação swagger:</strong>

- Somente entrar no link: http://localhost:8080/swagger-ui/index.html após a inicialização da aplicação.

