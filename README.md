# Desafio Backend
Confira o enunciado completo, [clique aqui](https://www.notion.so/Desafio-t-cnico-2a12f75979cd8085b6d9f166e49f0b85)

## Regras de negócios
- RN001 - O código de um cupom é um campo alfanumérico com um tamanho padrão de 6 caracteres.
- RN002 - Caracteres especiais podem ser aceitos na criação, contudo precisam ser removidos pela aplicação antes de salvar e retornar na resposta, garantindo o tamanho de 6 caracteres.
- RN003 - O valor de desconto do cupom possui um saldo mínimo de 0,5 sem máximo predeterminado.
- RN004 - O cupom nunca pode ser criado com data de expiração no passado.
- RN005 - Um cupom pode ser criado como já publicado.
- RN006 - Um cupom pode ser deletado a qualquer momento.
- RN007 - Deve ser feito um soft delete do cupom no banco de dados, garantindo a não perda de informações recebidas no cadastro.
- RN008 - Não deve ser possível deletar um cupom já deletado.

## Tecnologias utilizadas
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- Lombok
- H2 Database
- Maven
- JUnit 5
- Mockito
- Jacoco
- Swagger/OpenAPI
- Docker
- Flyway

## Chamadas

#### URL SWAGGER-DOC
http://localhost:8080/swagger-ui/index.html

#### URL chamada POST (Salvar um cupom)
http://localhost:8080/coupon
```json
{
  "code": "ABC123",
  "description": "Cupom de desconto de boas-vindas",
  "discountValue": 0.5,
  "expirationDate": "2026-12-31T23:59:59"
}
```

#### URL chamada DELETE (Deletar um cupom)
http://localhost:8080/coupon/{code}







