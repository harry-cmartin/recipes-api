# Recipes API 🍲

Uma API RESTful para gerenciamento de receitas culinárias, construída com Spring Boot. A aplicação permite a criação, listagem, atualização, exclusão de receitas e também um sistema de "likes" para cada prato.

Aplicação tem por objetivo treinar, relembrar conceitos e ser utilizada de modelo para deploy em nuvem para testes.

## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando as seguintes tecnologias e padrões:

*   **Java 21**: Linguagem de programação.
*   **Spring Boot**: Framework principal da aplicação.
*   **Spring MVC**: Para a construção dos endpoints REST (`@RestController`, `@RequestMapping`, etc).
*   **Spring Data JPA**: Para abstração do acesso ao banco de dados e persistência de dados.
*   **Jakarta EE**: Especificações do Java Enterprise (ex: anotações de persistência `jakarta.persistence.*`).
*   **Lombok**: Para redução de código boilerplate (`@Getter`, `@Setter`, `@AllArgsConstructor`, `@Builder`, etc).
*   **Banco de Dados**: Gerenciado via JPA/Hibernate com entidades (`@Entity`).
*   **Maven**: Gerenciador de dependências e build do projeto.
*   **Docker**: O projeto possui suporte à conteinerização (`docker-compose.yml`).

## ⚙️ Padrão de Arquitetura

O projeto adota uma arquitetura em camadas bem definida, seguindo boas práticas de separação de responsabilidades (Separation of Concerns). Utilizamos o padrão **MVC (Model-View-Controller)** adaptado para APIs REST, somado ao padrão **DTO (Data Transfer Object)**.

### Arquitetura de Pastas

A estrutura do projeto dentro de `src/main/java/com/recipes_api/fabrica` é organizada da seguinte forma:


# Estrutura do Projeto

```text
com.recipes_api.fabrica
├── controller
│   └── Recebe as requisições HTTP, repassa para os serviços e retorna as respostas (ResponseEntity).
│
├── dto
│   └── Objetos de Transferência de Dados (DTOs), usados para receber/enviar dados para o cliente sem expor as entidades do banco.
│
├── entity
│   └── Classes mapeadas para tabelas no banco de dados usando JPA/Hibernate.
│
├── mappers
│   └── Classes ou interfaces responsáveis por converter entidades em DTOs e vice-versa.
│
├── repository
│   └── Interfaces Spring Data JPA para acesso e manipulação dos dados no banco.
│
├── service
│   ├── Contém as regras de negócio. O controller chama o service, que por sua vez utiliza o repository.
│   ├── RecipeService.java
│   │   └── Interface com as definições dos serviços.
│   └── RecipesServiceImpl.java
│       └── Implementação das regras de negócio definidas na interface.
│
└── FabricaApplication.java
    └── Classe principal que inicia a aplicação Spring Boot.
```

## 📡 Endpoints da API

A URL base para todos os endpoints é: `/api/recipes`

### 1. Listar Receitas Paginadas
*   **Método:** `GET`
*   **URL:** `/api/recipes`
*   **Descrição:** Retorna uma lista de receitas com suporte a paginação e ordenação. Por padrão, retorna 10 itens por página, ordenados decrescentemente pela data de criação (`createdAt`).
*   **Query Params Opcionais:** `?page=0&size=10&sort=createdAt,desc`
*   **Resposta de Sucesso:** `200 OK` (Objeto Page do Spring contendo uma lista de `RecipesDto`)

### 2. Criar Receita
*   **Método:** `POST`
*   **URL:** `/api/recipes`
*   **Descrição:** Cria uma nova receita no sistema.
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "category": "Sobremesa",
      "name": "Pudim de Leite",
      "description": "Receita tradicional de pudim com calda de caramelo."
    }
    ```
    *(Nota: `id`, `likes` e `createdAt` são gerados automaticamente).*
*   **Resposta de Sucesso:** `201 Created` e retorna o `RecipesDto` criado. A URL do novo recurso é enviada no header `Location`.

### 3. Dar "Like" em uma Receita
*   **Método:** `POST`
*   **URL:** `/api/recipes/{id}/like`
*   **Descrição:** Incrementa em 1 o número de "likes" de uma receita específica.
*   **Parâmetros de Rota:** `id` (ID da receita).
*   **Resposta de Sucesso:** Status code da operação (geralmente `200 OK` ou `204 No Content` dependendo da implementação do Service).

### 4. Atualizar Receita Parcialmente
*   **Método:** `PATCH`
*   **URL:** `/api/recipes/{id}`
*   **Descrição:** Atualiza os dados de uma receita existente.
*   **Parâmetros de Rota:** `id` (ID da receita).
*   **Corpo da Requisição (JSON):** Qualquer campo do DTO que deseje atualizar, por exemplo:
    ```json
    {
      "description": "Receita atualizada de pudim, agora com menos açúcar."
    }
    ```
*   **Resposta de Sucesso:** `200 OK` (Vazio)

### 5. Excluir Receita
*   **Método:** `DELETE`
*   **URL:** `/api/recipes/remove/{id}`
*   **Descrição:** Remove uma receita do banco de dados.
*   **Parâmetros de Rota:** `id` (ID da receita).
*   **Resposta de Sucesso:** `200 OK` (Vazio)