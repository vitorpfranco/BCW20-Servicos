package com.soulcode.Servicos.Repositories;

import com.soulcode.Servicos.Models.Chamado;
import com.soulcode.Servicos.Models.ChamadoGroup;
import com.soulcode.Servicos.Models.Cliente;
import com.soulcode.Servicos.Models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reactor.util.function.Tuple2;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ChamadoRepository extends JpaRepository<Chamado,Integer> {

    List<Chamado> findByCliente(Optional<Cliente> cliente);

    List<Chamado> findByFuncionario(Optional<Funcionario> funcionario);

    @Query(value = "SELECT * FROM chamado WHERE status =:status",nativeQuery = true )
    List<Chamado> findByStatus(String status);

    @Query(value="SELECT * FROM chamado WHERE data_entrada BETWEEN :data1 AND :data2", nativeQuery = true)
    List<Chamado> findByIntervaloData(Date data1, Date data2);

    @Query(value = "SELECT new com.soulcode.Servicos.Models.ChamadoGroup(C.status, COUNT(C.idChamado)) from Chamado C GROUP BY C.status")
    List<ChamadoGroup> groupByStatus();
}

