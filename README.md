# Gerenciamento-equipes

## Diagrama de Classes

```mermaid
classDiagram
    %% ENTIDADES PRINCIPAIS
    class Usuario {
        <<UUID>> id
        +String nome
        +String sobrenome
        +String cpf
        +String email
        +String telefone
        +String senha
    }

    class Equipe {
        <<UUID>> id
        +String nome
        +List<Usuario> membros
        +List<Tarefa> tarefas
    }

    class Tarefa {
        <<UUID>> id
        +String titulo
        +String descricao
        +LocalDateTime dataCriacao
        +LocalDate prazo
        +LocalDateTime dataTermino
        +Prioridade prioridade
        +StatusTarefa status
        +Usuario responsavel
        +Equipe equipe
        +List<Comentario> comentarios
    }

    class Comentario {
        <<UUID>> id
        +String conteudo
        +LocalDateTime dataCriacao
        +Usuario autor
        +Tarefa tarefa
    }

    class RecuperacaoSenha {
        <<UUID>> id
        +String token
        +LocalDateTime dataCriacao
        +LocalDateTime dataExpiracao
        +Usuario usuario
    }

    %% ENUMS
    class Prioridade {
        <<enum>>
        BAIXA
        MEDIA
        ALTA
    }

    class StatusTarefa {
        <<enum>>
        A_FAZER
        EM_ANDAMENTO
        CONCLUIDA
    }

    %% RELACIONAMENTOS
    Equipe "1" --> "0..*" Usuario : possui
    Equipe "1" --> "0..*" Tarefa : gerencia
    Usuario "1" --> "0..*" Tarefa : responsavelPor
    Usuario "1" --> "0..*" Comentario : escreve
    Tarefa "1" --> "0..*" Comentario : contem
    Usuario "1" --> "0..*" RecuperacaoSenha : possui

