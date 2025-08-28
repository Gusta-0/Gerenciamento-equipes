# Gerenciamento-equipes

## Diagrama de Classes

```mermaid
classDiagram
    %% ENTIDADES PRINCIPAIS
    class Usuario {
        +Long id
        +String nome
        +String email
        +String senha
        +Papel papel
        +Equipe equipe
    }

    class Equipe {
        +Long id
        +String nome
        +List<Usuario> membros
        +List<Tarefa> tarefas
    }

    class Tarefa {
        +Long id
        +String titulo
        +String descricao
        +LocalDate prazo
        +Prioridade prioridade
        +StatusTarefa status
        +Usuario responsavel
        +Equipe equipe
        +List<Comentario> comentarios
    }

    class Comentario {
        +Long id
        +String conteudo
        +LocalDateTime criadoEm
        +Usuario autor
    }

    class RegistroAtividade {
        +Long id
        +String acao
        +LocalDateTime dataHora
        +Usuario usuario
        +Tarefa tarefa
    }

    class Relatorio {
        +Long id
        +TipoRelatorio tipo
        +LocalDate geradoEm
        +Usuario geradoPor
    }

    %% ENUMS
    class Papel {
        <<enum>>
        ADMINISTRADOR
        MEMBRO
    }

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

    class TipoRelatorio {
        <<enum>>
        PDF
        EXCEL
    }

    %% RELACIONAMENTOS
    Equipe "1" --> "0..*" Usuario : possui
    Equipe "1" --> "0..*" Tarefa : gerencia
    Usuario "1" --> "0..*" Tarefa : responsavelPor
    Usuario "1" --> "0..*" Comentario : escreve
    Usuario "1" --> "0..*" RegistroAtividade : executa
    Tarefa "1" --> "0..*" Comentario : contem
    Tarefa "1" --> "0..*" RegistroAtividade : registrado
    Relatorio "1" --> "1" Usuario : geradoPor
