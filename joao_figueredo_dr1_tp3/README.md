# Seção 1: Estrutura de Pacotes

## Estrutura de Pacotes

```bash
src
├── main
│   ├── java
│   │   ├── com
│   │   │   ├── exemplo
│   │   │   │   ├── actuator
│   │   │   │   │   └── CustomMetricsEndpoint.java
│   │   │   │   ├── config
│   │   │   │   │   └── RedisConfig.java
│   │   │   │   ├── controller
│   │   │   │   │   ├── AlunoController.java
│   │   │   │   │   ├── CursoController.java
│   │   │   │   │   ├── CursoRedisController.java
│   │   │   │   │   └── MaterialDidaticoController.java
│   │   │   │   ├── entity
│   │   │   │   │   ├── Aluno.java
│   │   │   │   │   ├── Curso.java
│   │   │   │   │   └── MaterialDidatico.java
│   │   │   │   ├── repository
│   │   │   │   │   ├── AlunoRepository.java
│   │   │   │   │   ├── CursoRepository.java
│   │   │   │   │   └── MaterialDidaticoRepository.java
│   │   │   │   ├── service
│   │   │   │   │   ├── AlunoService.java
│   │   │   │   │   ├── CursoRedisService.java
│   │   │   │   │   ├── CursoService.java
│   │   │   │   │   └── MaterialDidaticoService.java
│   │   │   │   ├── Application.java
│   │   │   │   └── DataLoader.java
│   ├── resources
│   │   ├── application.properties
│   │   └── data.sql
└── test
    └── java
        └── com
            └── exemplo
                └── ApplicationTests.java

```

# Seção 2: Instalação

## Instalação

### Pré-requisitos

Certifique-se de ter instalado:

- JDK 11 ou superior
- Maven
- Docker (opcional, para Redis e MongoDB)

### Passo a Passo

1. **Clone o repositório**

   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```

2. **Configure o Redis e MongoDB**

   Se estiver usando Docker, execute os seguintes comandos para iniciar as instâncias do Redis e MongoDB:

   ```bash
   docker run -d -p 6379:6379 --name redis redis
   docker run -d -p 27017:27017 --name mongodb mongo
   ```

3. **Configure o arquivo application.properties**

   ```bash
   Verifique o arquivo src/main/resources/application.properties para confirmar as configurações de Redis, MongoDB e H2.

   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.h2.console.enabled=true

   spring.redis.host=localhost
   spring.redis.port=6379

   spring.data.mongodb.uri=mongodb://localhost:27017/your_database_name

   management.endpoints.web.exposure.include=*
   management.endpoint.health.show-details=always
   ```

4. **Instale as dependências e compile o projeto**

   ```bash
   mvn clean install
   ```

5. Execute a aplicação
   ```bash
   mvn spring-boot:run
   ```

### Testando a Aplicação

    Acesse a aplicação em http://localhost:8080.

# Seção 3: Utilização

## Endpoints Disponíveis

### Aluno

- **GET /alunos**

  - Descrição: Obtém todos os alunos.
  - Exemplo de resposta:
    ```json
    [
      {
        "id": 1,
        "nome": "João",
        "cursos": [
          {
            "id": 1,
            "nome": "Matemática"
          },
          {
            "id": 2,
            "nome": "História"
          }
        ]
      },
      {
        "id": 2,
        "nome": "Maria",
        "cursos": [
          {
            "id": 1,
            "nome": "Matemática"
          }
        ]
      }
    ]
    ```

- **GET /alunos/{id}**

  - Descrição: Obtém um aluno pelo ID.
  - Exemplo de resposta:
    ```json
    {
      "id": 1,
      "nome": "João",
      "cursos": [
        {
          "id": 1,
          "nome": "Matemática"
        },
        {
          "id": 2,
          "nome": "História"
        }
      ]
    }
    ```

- **POST /alunos**

  - Descrição: Cria um novo aluno.
  - Exemplo de corpo de requisição:
    ```json
    {
      "nome": "Ana",
      "cursos": [
        {
          "id": 1
        }
      ]
    }
    ```

- **PUT /alunos/{id}**

  - Descrição: Atualiza um aluno existente.
  - Exemplo de corpo de requisição:
    ```json
    {
      "nome": "Ana Maria",
      "cursos": [
        {
          "id": 1
        }
      ]
    }
    ```

- **DELETE /alunos/{id}**
  - Descrição: Deleta um aluno pelo ID.

### Curso

- **GET /cursos**

  - Descrição: Obtém todos os cursos.

- **GET /cursos/{id}**

  - Descrição: Obtém um curso pelo ID.

- **POST /cursos**

  - Descrição: Cria um novo curso.
  - Exemplo de corpo de requisição:
    ```json
    {
      "nome": "Geografia"
    }
    ```

- **PUT /cursos/{id}**

  - Descrição: Atualiza um curso existente.

- **DELETE /cursos/{id}**
  - Descrição: Deleta um curso pelo ID.

### Curso (Redis Cache)

- **POST /redis/cursos/cache**

  - Descrição: Cacheia todos os cursos.

- **GET /redis/cursos**

  - Descrição: Obtém todos os cursos do cache.

- **POST /redis/cursos/evict**
  - Descrição: Limpa o cache dos cursos.

### Material Didático (MongoDB)

- **GET /materiais**

  - Descrição: Obtém todos os materiais didáticos.

- **GET /materiais/{id}**

  - Descrição: Obtém um material didático pelo ID.

- **POST /materiais**

  - Descrição: Cria um novo material didático.
  - Exemplo de corpo de requisição:
    ```json
    {
      "titulo": "Livro de Biologia",
      "descricao": "Um livro sobre biologia"
    }
    ```

- **PUT /materiais/{id}**

  - Descrição: Atualiza um material didático existente.

- **DELETE /materiais/{id}**
  - Descrição: Deleta um material didático pelo ID.

## Monitoramento

### Actuator

- **GET /actuator/health**

  - Descrição: Verifica o estado de saúde da aplicação.

- **GET /actuator/metrics**

  - Descrição: Exibe as métricas da aplicação.

- **GET /actuator/customMetric**
  - Descrição: Exemplo de métrica customizada.

# Seção 4: Conclusão

Este projeto demonstra a competência em:

- Persistência de dados utilizando JPA com H2.
- Gerenciamento de cache usando Redis.
- Armazenamento de documentos com MongoDB.
- Monitoramento da aplicação utilizando Spring Boot Actuator.

## Futuras Melhorias

- Implementar autenticação e autorização para proteger os endpoints.
- Adicionar testes unitários e de integração para garantir a qualidade do código.
- Implementar tratamento de erros global para fornecer respostas consistentes em caso de falhas.
- Integrar com um serviço de mensagens (como RabbitMQ ou Kafka) para a comunicação entre diferentes partes da aplicação.

## Contribuições

Sinta-se à vontade para contribuir com este projeto através de pull requests. Para problemas ou sugestões, abra uma issue no GitHub.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para obter mais informações.

---

Feito com :heart: por João Figueredo.
