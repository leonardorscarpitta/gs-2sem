Integrantes do grupo
Leonardo Rocha Scarpitta 555460
Murilo Justi Rodrigues 554512
Gabriel Matias Simões 556171

# VitaFlow 🏥

Sistema de gerenciamento de pacientes e registros diários para acompanhamento de saúde mental desenvolvido com Spring Boot.

## 📖 Sobre o Projeto

O **VitaFlow** é uma aplicação web completa para monitoramento e gestão de pacientes com foco em saúde mental. O sistema permite o cadastro de pacientes, acompanhamento diário de seu estado mental, qualidade de sono e humor, facilitando o trabalho de profissionais de saúde no acompanhamento contínuo de seus pacientes.

### Principais Funcionalidades

- Cadastro e gerenciamento completo de pacientes
- Registro diário de acompanhamento (estado mental, qualidade de sono e humor)
- Interface web amigável com Thymeleaf
- API REST para integração com outros sistemas
- Tratamento robusto de exceções
- Banco de dados H2 em memória para facilitar desenvolvimento e testes
- Documentação do Swagger que pode ser acessada em `/swagger-ui/index.html#/`

## 🏗️ Arquitetura e Camadas

O projeto segue a **arquitetura em camadas** (Layered Architecture) do Spring Boot, promovendo separação de responsabilidades e facilidade de manutenção:

### 1️⃣ Camada de Apresentação (`controller/`)

Responsável por **expor os endpoints** e gerenciar as requisições HTTP. Esta camada não contém lógica de negócio, apenas recebe requisições, delega para a camada de serviço e retorna respostas.

**Controladores:**

- **`PacienteController`** - API REST para operações com pacientes
- **`RegistroDiarioController`** - API REST para operações com registros diários
- **`WebController`** - Controlador web que renderiza as páginas Thymeleaf

### 2️⃣ Camada de Serviço (`service/`)

Contém toda a **lógica de negócio** da aplicação. Esta camada processa as regras, validações e orquestra as operações entre diferentes entidades.

**Serviços:**

- **`PacienteService`** - Gerencia regras de negócio relacionadas a pacientes (validações, conversões, etc.)
- **`RegistroDiarioService`** - Gerencia a criação e atualização de registros diários de pacientes
- **`ConvertControllersResponseEntities`** - Utilitário para padronizar respostas HTTP dos controladores

### 3️⃣ Camada de Persistência (`repository/`)

Interface com o **banco de dados** através do Spring Data JPA. Fornece métodos prontos para operações CRUD e permite criar queries customizadas.

**Repositórios:**

- **`PacienteRepository`** - Acesso aos dados de pacientes (busca por CPF, listagem, etc.)
- **`RegistroDiarioRepository`** - Acesso aos dados de registros diários

### 4️⃣ Camada de Modelo (`model/`)

Define as **entidades do domínio** que representam as tabelas do banco de dados através de anotações JPA.

**Entidades:**

- **`Paciente`**
  - `id` (Integer) - Identificador único
  - `nome` (String) - Nome completo do paciente
  - `dataNascimento` (LocalDateTime) - Data de nascimento
  - `email` (String) - E-mail de contato
  - `cpf` (String) - CPF do paciente (identificador único de negócio)
  - `registrosDiarios` (List) - Relacionamento um-para-muitos com registros

- **`RegistroDiario`**
  - `id` (Integer) - Identificador único
  - `paciente` (Paciente) - Relacionamento muitos-para-um com Paciente
  - `estadoMental` (EstadoMental) - Estado mental atual (BURNOUT, ANSIEDADE, ESTRESSE)
  - `qualidadeSono` (Integer) - Avaliação da qualidade do sono (escala numérica)
  - `qualidadeHumor` (Integer) - Avaliação da qualidade do humor (escala numérica)

- **`EstadoMental`** (Enum)
  - `BURNOUT` - Estado de esgotamento profissional
  - `ANSIEDADE` - Estado de ansiedade
  - `ESTRESSE` - Estado de estresse

### 5️⃣ Camada de Transferência (`dto/`)

Contém os **Data Transfer Objects** que definem a estrutura de entrada e saída da API, desacoplando a representação interna (entidades) da representação externa.

**DTOs:**

- **`PacienteRequestDTO`** - Dados recebidos para criar/atualizar paciente
- **`PacienteResponseDTO`** - Dados retornados ao consultar paciente
- **`RegistroDiarioRequestDTO`** - Dados recebidos para criar/atualizar registro
- **`RegistroDiarioResponseDTO`** - Dados retornados ao consultar registro

### 6️⃣ Camada de Mapeamento (`mapper/`)

Responsável pela **conversão entre entidades e DTOs**, centralizando a lógica de transformação de dados.

**Mappers:**

- **`PacienteMapper`** - Converte entre Paciente ↔ PacienteDTO
- **`RegistroDiarioMapper`** - Converte entre RegistroDiario ↔ RegistroDiarioDTO

### 7️⃣ Camada de Tratamento de Exceções (`exception/`)

Gerencia **exceções personalizadas** e padroniza as respostas de erro da aplicação.

**Componentes:**

- **`GlobalExceptionHandler`** - Intercepta exceções globalmente usando @ControllerAdvice
- **`InvalidoException`** - Exceção lançada quando dados inválidos são fornecidos
- **`NaoEncontradoException`** - Exceção lançada quando um recurso não é encontrado

## 🔌 Endpoints da API

### API REST - Pacientes (`/paciente`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/paciente/registrar` | Cadastra um novo paciente |
| `GET` | `/paciente/buscar/{cpf}` | Busca paciente por CPF |
| `GET` | `/paciente/buscar` | Lista todos os pacientes |
| `POST` | `/paciente/atualizar/{cpf}` | Atualiza dados de um paciente |
| `POST` | `/paciente/remover/{cpf}` | Remove um paciente |

### API REST - Registros Diários (`/diario`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/diario/registrar` | Cria um novo registro diário |
| `GET` | `/diario/buscar/{id}` | Busca registro por ID |
| `GET` | `/diario/buscar` | Lista todos os registros |
| `PUT` | `/diario/atualizar/{id}` | Atualiza um registro existente |
| `POST` | `/diario/remover/{id}` | Remove um registro |

### Interface Web (Thymeleaf)

| Rota | Descrição |
|------|-----------|
| `/` | Página inicial (redireciona para `/pacientes`) |
| `/pacientes` | Lista de pacientes |
| `/pacientes/novo` | Formulário de cadastro de paciente |
| `/pacientes/editar/{cpf}` | Formulário de edição de paciente |
| `/registros` | Lista de registros diários |
| `/registros/novo` | Formulário de novo registro |
| `/registros/editar/{id}` | Formulário de edição de registro |

## 🚀 Tecnologias

- **Java 17+** - Linguagem de programação
- **Spring Boot 3.x** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - API REST
- **Thymeleaf** - Template engine para interface web
- **H2 Database** - Banco de dados em memória
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciador de dependências e build

## ⚙️ Configuração

### Banco de Dados H2

O projeto utiliza H2 em memória configurado no `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:vitaflowdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

### Como Executar

```bash
# Compilar o projeto
./mvnw clean install

# Executar a aplicação
./mvnw spring-boot:run
```


A aplicação estará disponível em: `http://localhost:8080`
Console H2: `http://localhost:8080/h2-console`
## 📁 Estrutura de Arquivos

```
vitaflow/
├── src/main/
│   ├── java/.../vitaflow/
│   │   ├── VitaflowApplication.java    # Classe principal
│   │   ├── controller/                  # Controladores REST e Web
│   │   ├── dto/                         # Objetos de transferência
│   │   ├── exception/                   # Tratamento de exceções
│   │   ├── mapper/                      # Conversores DTO ↔ Entity
│   │   ├── model/                       # Entidades JPA
│   │   ├── repository/                  # Interfaces de persistência
│   │   └── service/                     # Lógica de negócio
│   └── resources/
│       ├── application.properties       # Configurações
│       ├── static/                      # CSS, JS, imagens
│       └── templates/                   # Templates Thymeleaf
└── pom.xml                              # Dependências Maven
```

## 👨‍💻 Desenvolvido com

Este projeto foi desenvolvido seguindo as melhores práticas de:
- Clean Architecture
- SOLID Principles
- REST API Design
- JPA/Hibernate Best Practices
