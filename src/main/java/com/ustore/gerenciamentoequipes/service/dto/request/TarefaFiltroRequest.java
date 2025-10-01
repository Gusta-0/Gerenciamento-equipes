package com.ustore.gerenciamentoequipes.service.dto.request;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Cargo;
import com.ustore.gerenciamentoequipes.infrastructure.enums.Prioridade;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusTarefa;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TarefaFiltroRequest {
    private String nomeIntegrante;
    private String titulo;
    private StatusTarefa status;
    private Prioridade prioridade;
    private UUID responsavelId;
    private Cargo departamento;
    private String projeto;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Boolean somenteAtrasadas;
}
