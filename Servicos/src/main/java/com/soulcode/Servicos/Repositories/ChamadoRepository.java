package com.soulcode.Servicos.Repositories;

import com.soulcode.Servicos.Models.Chamado;
import com.soulcode.Servicos.Models.Cliente;
import com.soulcode.Servicos.Models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ChamadoRepository extends JpaRepository<Chamado,Integer> {

    List<Chamado> findByCliente(Optional<Cliente> cliente);

    List<Chamado> findByFuncionario(Optional<Funcionario> funcionario);

    @Query(value = "SELECT * FROM chamado WHERE status =:status",nativeQuery = true )
    List<Chamado> findByStatus(String status);

    @Query(value = "SELECT chamado.*, pagamento.status\n" +
            "FROM chamado\n" +
            "INNER JOIN pagamento ON chamado.id_pagamento = pagamento.id_pagamento\n" +
            "WHERE pagamento.status = \"QUITADO\"",nativeQuery = true )
    List<Chamado> findByStatusQuitado();

    @Query(value = "SELECT chamado.*, pagamento.status\n" +
            "FROM chamado\n" +
            "INNER JOIN pagamento ON chamado.id_pagamento = pagamento.id_pagamento\n" +
            "WHERE pagamento.status = \"LANCADO\"",nativeQuery = true )
    List<Chamado> findByStatusLancado();

    @Query(value="SELECT * FROM chamado WHERE data_entrada BETWEEN :data1 AND :data2", nativeQuery = true)
    List<Chamado> findByIntervaloData(Date data1, Date data2);

    @Query(value = "SELECT \n" +
            "COUNT(CASE WHEN chamado.status = 'RECEBIDO' THEN 1 END) AS Recebido,\n" +
            "COUNT(CASE WHEN chamado.status = 'ATRIBUIDO' THEN 1 END) AS Atribuido,\n" +
            "COUNT(CASE WHEN chamado.status = 'CONCLUIDO' THEN 1 END) AS Concluido,\n" +
            "COUNT(CASE WHEN chamado.status = 'ARQUIVADO' THEN 1 END) AS Arquivado\n" +
            "FROM  chamado", nativeQuery = true)
    Object qtdChamadosPorStatus();

    @Query(value = "SELECT funcionario.nome,\n" +
            "COUNT(CASE WHEN chamado.status = 'ATRIBUIDO' THEN 1 END) AS Atribuido,\n" +
            "COUNT(CASE WHEN chamado.status = 'CONCLUIDO' THEN 1 END) AS Concluido,\n" +
            "COUNT(CASE WHEN chamado.status = 'ARQUIVADO' THEN 1 END) AS Arquivado,\n" +
            "COUNT(chamado.status) AS Total\n" +
            "FROM  chamado\n" +
            "JOIN funcionario ON chamado.id_funcionario = funcionario.id_funcionario\n" +
            "GROUP BY chamado.id_funcionario;", nativeQuery = true)
    List<Object> qtdChamadosPorFuncionario();

    @Query(value = "SELECT cliente.nome, SUM(pagamento.valor)\n" +
            "FROM chamado JOIN cliente ON chamado.id_cliente = cliente.id_cliente\n" +
            "JOIN pagamento ON chamado.id_pagamento = pagamento.id_pagamento\n" +
            "WHERE chamado.status = 'CONCLUIDO' AND cliente.nome = :nome", nativeQuery = true)
    List<Object> totalPagoPorCliente(String nome);
}
