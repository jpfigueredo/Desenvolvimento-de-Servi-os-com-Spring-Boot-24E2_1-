DR4-AT Project
Descrição
Este é um projeto Spring Boot que implementa uma aplicação web para gerenciar funcionários, departamentos e usuários com segurança utilizando Spring Security. A aplicação utiliza H2 para persistência de dados relacionais e MongoDB para armazenamento de dados de usuários.

Pré-requisitos
JDK 11 ou superior
Maven ou Gradle
Docker (para executar a aplicação em contêiner)
MongoDB
Configuração do Projeto

1. Clonar o Repositório
   bash
   Copiar código
   git clone <https://github.com/seu-usuario/dr4-at.git>
   cd dr4-at
2. Configurar o MongoDB
   Certifique-se de ter o MongoDB em execução na sua máquina local ou em um servidor. Atualize a configuração do MongoDB no arquivo application.properties conforme necessário:

properties
Copiar código
spring.data.mongodb.uri=mongodb://localhost:27017/dr4-at 3. Executar o Projeto com Maven
Para compilar e executar o projeto, use os seguintes comandos:

bash
Copiar código
mvn clean install
mvn spring-boot:run 4. Executar o Projeto com Docker
Crie o JAR executável:

bash
Copiar código
mvn clean package
Crie e execute o contêiner Docker:

bash
Copiar código
docker build -t dr4-at .
docker run -p 8080:8080 dr4-at
Endpoints da API
Abaixo estão os endpoints disponíveis para interagir com a aplicação:

Funcionario
Criar um novo Funcionário

POST /api/funcionarios
Corpo da Requisição:
json
Copiar código
{
"nome": "João Silva",
"endereco": "Rua A, 123",
"telefone": "123456789",
"email": "<joao.silva@example.com>",
"dataNascimento": "1990-01-01",
"departamentoId": 1
}
Listar todos os Funcionários

GET /api/funcionarios
Atualizar um Funcionário existente

PUT /api/funcionarios/{id}
Corpo da Requisição:
json
Copiar código
{
"nome": "João Silva",
"endereco": "Rua B, 456",
"telefone": "987654321",
"email": "<joao.silva@example.com>",
"dataNascimento": "1990-01-01",
"departamentoId": 1
}
Deletar um Funcionário

DELETE /api/funcionarios/{id}
Departamento
Criar um novo Departamento

POST /api/departamentos
Corpo da Requisição:
json
Copiar código
{
"nome": "Tecnologia",
"local": "São Paulo"
}
Listar todos os Departamentos

GET /api/departamentos
Atualizar um Departamento existente

PUT /api/departamentos/{id}
Corpo da Requisição:
json
Copiar código
{
"nome": "Tecnologia da Informação",
"local": "São Paulo"
}
Deletar um Departamento

DELETE /api/departamentos/{id}
Usuario
Criar um novo Usuário

POST /api/usuarios
Corpo da Requisição:
json
Copiar código
{
"nome": "admin",
"senha": "senha123",
"papel": "ADMIN"
}
Listar todos os Usuários

GET /api/usuarios
Atualizar um Usuário existente

PUT /api/usuarios/{id}
Corpo da Requisição:
json
Copiar código
{
"nome": "admin",
"senha": "novaSenha123",
"papel": "USER"
}
Deletar um Usuário

DELETE /api/usuarios/{id}
Segurança
A aplicação utiliza Spring Security para proteger os endpoints da API. Para acessar os endpoints protegidos, você precisará autenticar-se com um nome de usuário e senha. Por padrão, o Spring Security está configurado para HTTP Basic Authentication.

Exemplo de Requisição Autenticada
Para acessar os endpoints protegidos, você pode usar o curl com as credenciais básicas:

bash
Copiar código
curl -u admin:senha123 -X GET <http://localhost:8080/api/funcionarios>
Testes de API
Você pode usar o Postman para testar os endpoints da API. Importar a seguinte coleção de testes Postman:

Criar um novo Funcionário e Departamento (POST)
Listar todos os Funcionários e Departamentos (GET)
Atualizar um Funcionário e Departamento existente (PUT)
Deletar um Funcionário e Departamento (DELETE)
Execução dos Testes com @SpringBootTest
Para executar os testes de integração do Spring Boot, você pode usar o seguinte comando:

bash
Copiar código
mvn test
Implementação de Slices de Teste
Certifique-se de que seus testes estejam configurados corretamente utilizando @SpringBootTest e outros slices de teste disponíveis no Spring Boot.

Conclusão
Este README forneceu uma visão geral sobre como configurar e executar o projeto Spring Boot, bem como informações detalhadas sobre como interagir com os endpoints da API. Para mais informações, consulte a documentação oficial do Spring Boot e do Spring Security.
