package com.soulcode.Servicos.Models;

public class ChamadoGroup {
    private StatusChamado status;
    private Long qt;

    public ChamadoGroup(StatusChamado status, Long qt) {
        this.status = status;
        this.qt = qt;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public Long getQt() {
        return qt;
    }

    public void setQt(Long qt) {
        this.qt = qt;
    }
}
