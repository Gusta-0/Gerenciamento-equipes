# Gerenciamento-equipes

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

