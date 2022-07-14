package com.soulcode.Servicos.Services;

import com.soulcode.Servicos.Models.Cargo;
import com.soulcode.Servicos.Repositories.CargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CargosServiceTest {
    @Mock
    private CargoRepository cargosRepository;

    private CargoService cargoService;

    @BeforeEach
    public void init() {
        cargoService = new CargoService(cargosRepository);
    }

    @Test
    void addCargoRemoveId() {
        when(cargosRepository.save(any(Cargo.class))).then(returnsFirstArg());
        Cargo cargo = new Cargo();
        cargo.setIdCargo(100);
        cargo.setNome("Técnico");
        cargo.setSalario(2000.0);
        cargo.setDescricao("Corrigir falhas nos chamados");
        Cargo cargoSalvo = cargoService.cadastrarCargo(cargo);
        assertThat(cargoSalvo.getIdCargo()).isNull();
    }
}
