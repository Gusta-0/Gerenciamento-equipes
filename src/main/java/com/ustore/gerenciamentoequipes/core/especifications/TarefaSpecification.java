package com.ustore.gerenciamentoequipes.core.especifications;

import com.ustore.gerenciamentoequipes.core.entity.Tarefa;
import com.ustore.gerenciamentoequipes.enums.Cargo;
import com.ustore.gerenciamentoequipes.enums.Prioridade;
import com.ustore.gerenciamentoequipes.enums.StatusTarefa;
import com.ustore.gerenciamentoequipes.payload.dto.request.TarefaFiltroRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TarefaSpecification {

    public static Specification<Tarefa> comNomeTarefa(String titulo) {
        return (root, query, cb) -> {
            if (titulo == null || titulo.isBlank()) return null;
            String like = "%" + titulo.toLowerCase() + "%";
            return cb.like(cb.lower(root.get("titulo")), like);
        };
    }

    public static Specification<Tarefa> comStatus(StatusTarefa status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Tarefa> comPrioridade(Prioridade prioridade) {
        return (root, query, cb) ->
                prioridade == null ? null : cb.equal(root.get("prioridade"), prioridade);
    }

    public static Specification<Tarefa> comResponsavel(UUID responsavelId) {
        return (root, query, cb) ->
                responsavelId == null ? null : cb.equal(root.join("responsavel").get("id"), responsavelId);
    }

    public static Specification<Tarefa> comDepartamento(Cargo departamento) {
        return (root, query, cb) ->
                departamento == null ? null : cb.equal(root.get("departamento"), departamento);
    }

    public static Specification<Tarefa> comProjeto(String projeto) {
        return (root, query, cb) ->
                (projeto == null || projeto.isBlank()) ? null :
                        cb.like(cb.lower(root.get("projeto")), "%" + projeto.toLowerCase() + "%");
    }

    public static Specification<Tarefa> comIntervaloDatas(LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, cb) -> {
            if (dataInicio != null && dataFim != null) {
                return cb.between(root.get("dataEntrega"), dataInicio.atStartOfDay(), dataFim.atTime(23, 59));
            }
            if (dataInicio != null) {
                return cb.greaterThanOrEqualTo(root.get("dataEntrega"), dataInicio.atStartOfDay());
            }
            if (dataFim != null) {
                return cb.lessThanOrEqualTo(root.get("dataEntrega"), dataFim.atTime(23, 59));
            }
            return null;
        };
    }

    public static Specification<Tarefa> somenteAtrasadas(Boolean somenteAtrasadas) {
        return (root, query, cb) -> {
            if (somenteAtrasadas == null || !somenteAtrasadas) return null;
            return cb.lessThan(root.get("dataEntrega"), LocalDateTime.now());
        };
    }

    public static Specification<Tarefa> comFiltros(TarefaFiltroRequest filtro) {
        return Specification.allOf(
//                comNomeIntegrante(filtro.getNomeIntegrante()),
                comStatus(filtro.getStatus()),
                comPrioridade(filtro.getPrioridade()),
                comResponsavel(filtro.getResponsavelId()),
                comDepartamento(filtro.getDepartamento()),
                comProjeto(filtro.getProjeto()),
                comIntervaloDatas(filtro.getDataInicio(), filtro.getDataFim()),
                somenteAtrasadas(filtro.getSomenteAtrasadas())
        );
    }
}
