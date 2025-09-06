

# 📌 Sistema de Gerenciamento de Equipes

O **Sistema de Gerenciamento de Equipes** é uma API REST desenvolvida em **Java + Spring Boot** que tem como objetivo otimizar a **organização, comunicação e produtividade** de times no ambiente corporativo.

A aplicação permite o **cadastro de usuários, equipes e tarefas**, com controle de permissões (👨‍💼 **Administrador** e 👤 **Usuário comum**), possibilitando que gestores criem tarefas, organizem equipes e acompanhem o progresso de forma eficiente.

---

## 🎯 Objetivos principais

* ✅ Facilitar o **cadastro e a gestão de equipes** e seus membros;
* ✅ Controlar permissões entre **administradores e usuários**;
* ✅ Permitir a **criação e atribuição de tarefas** com prazos, prioridades e status;
* ✅ Monitorar o **andamento das tarefas** em tempo real;
* ✅ Gerar relatórios e históricos de atividades (futuro extra).

---

## 🔧 Funcionalidades principais

* 👥 **Usuários**

  * CRUD completo (criar, listar, atualizar, excluir).
  * Atribuição de **papéis (Role)** → `ADMIN` e `USER`.
  * Recuperação de senha via **token temporário**.

* 🛡 **Administrador**

  * Pode **criar equipes**.
  * Pode **atribuir tarefas** a usuários.
  * Controle de permissões com **Spring Security**.

* 👨‍👩‍👧 **Equipes**

  * Cadastro de equipes.
  * Associação de **usuários membros**.
  * Consulta de **tarefas vinculadas** à equipe.

* ✅ **Tarefas**

  * CRUD completo (criar, listar, atualizar, excluir).
  * Associação a um **usuário responsável** e a uma **equipe**.
  * Controle de **status** → `A_FAZER`, `EM_ANDAMENTO`, `CONCLUIDA`.
  * Controle de **prioridade** → `BAIXA`, `MEDIA`, `ALTA`.

---

## 🛠 Tecnologias utilizadas

* ☕ **Java 22**
* 🍃 **Spring Boot 3** (Web, Data JPA, Validation, Security)
* 🐘 **PostgreSQL**
* 🔐 **Spring Security + JWT** (autenticação/autorização)
* 🛠 **MapStruct** (DTO ↔ Entidade)
* 📊 **Lombok** (boilerplate reduction)
* 🐳 **Docker** (containerização)

---

## 📂 Estrutura da API (CRUDs disponíveis)

### 👥 Usuário

* `POST /usuarios` → criar usuário
* `GET /usuarios` → listar todos
* `GET /usuarios/{id}` → buscar por id
* `PUT /usuarios/{id}` → atualizar
* `DELETE /usuarios/{id}` → remover

### 👨‍👩‍👧 Equipe

* `POST /equipes` → criar equipe (apenas **admin**)
* `GET /equipes` → listar todas
* `GET /equipes/{id}` → buscar por id
* `PUT /equipes/{id}` → atualizar
* `DELETE /equipes/{id}` → remover

### 📌 Tarefa

* `POST /tarefas` → criar tarefa (apenas **admin**)
* `GET /tarefas` → listar todas
* `GET /tarefas/{id}` → buscar por id
* `PUT /tarefas/{id}` → atualizar
* `DELETE /tarefas/{id}` → remover

### 🔑 Recuperação de Senha

* `POST /recuperacao-senha` → solicitar token
* `POST /recuperacao-senha/validar` → validar token
* `PUT /recuperacao-senha/redefinir` → redefinir senha

---

## 🐳 Como rodar com Docker

### 1️⃣ Gerar o build da aplicação

```bash
./mvnw clean package -DskipTests
```

### 2️⃣ Construir a imagem Docker

```bash
docker build -t gerenciamento-equipes .
```

### 3️⃣ Rodar o container com PostgreSQL e a API

```bash
docker-compose up -d
```

### 4️⃣ Acessar a API

* 📡 URL base: `http://localhost:8080/api`
* 📘 Swagger/OpenAPI (se configurado): `http://localhost:8080/swagger-ui.html`

---

## 🚀 Benefícios

* 📊 Clareza nas responsabilidades e prazos.
* 💬 Melhoria da comunicação interna.
* 🔒 Segurança com controle de permissões.
* 📈 Escalabilidade para times maiores.

---


## Diagrama de Classes

```mermaid
classDiagram

    class Usuario {
        +UUID id
        +String nome
        +String sobrenome
        +String cpf
        +String email
        +String telefone
        +String senha
    }

    class Administrador {
        // tipo de Usuario com menos atributos
    }

    class Role {
        +UUID id
        +String nome
    }

    class Equipe {
        +UUID id
        +String nome
        +List~Usuario~ membros
        +List~Tarefa~ tarefas
    }

    class Tarefa {
        +UUID id
        +String titulo
        +String comentario
        +LocalDateTime dataCriacao
        +LocalDateTime prazo
        +LocalDateTime dataTermino
        +Prioridade prioridade
        +StatusTarefa status
    }

    class RecuperacaoSenha {
        +UUID id
        +String token
        +LocalDateTime dataCriacao
        +LocalDateTime dataExpiracao
    }

    class Prioridade {
        <<enumeration>>
        BAIXA
        MEDIA
        ALTA
    }

    class StatusTarefa {
        <<enumeration>>
        A_FAZER
        EM_ANDAMENTO
        CONCLUIDA
    }

    %% Herança
    Administrador --|> Usuario

    %% Relacionamentos
    Usuario --> Role : ocupa
    Equipe "1" --> "0..*" Usuario : membros
    Equipe "1" --> "0..*" Tarefa : tarefas
    Tarefa "1" --> "1" Usuario : responsavel
    Tarefa "1" --> "1" Equipe : pertence
    RecuperacaoSenha "1" --> "1" Usuario : possui
    Tarefa --> Prioridade
    Tarefa --> StatusTarefa

