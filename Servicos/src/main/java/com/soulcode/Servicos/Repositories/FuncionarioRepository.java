package com.soulcode.Servicos.Repositories;

import com.soulcode.Servicos.Models.Cargo;
import com.soulcode.Servicos.Models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByEmail(String email);

    List<Funcionario> findByCargo(Optional<Cargo> cargo);

    @Query(value = "SELECT funcionario.*\n" +
            "FROM funcionario\n" +
            "LEFT JOIN chamado ON chamado.id_funcionario = funcionario.id_funcionario\n" +
            "WHERE chamado.id_funcionario IS NULL",nativeQuery = true )
    List<Funcionario> findFuncSemChamado();

    @Query(value = "SELECT COUNT(id_cargo) as funcionarios\n" +
            "FROM funcionario\n" +
            "WHERE id_cargo = :idCargo ", nativeQuery = true)
    List<Object> qtdFuncPorCargo(Integer idCargo);

    @Query(value = "SELECT funcionario.*\n" +
            "FROM funcionario\n" +
            "WHERE foto = ''", nativeQuery = true)
    List<Funcionario> funcFotoNull();

    @Query(value = "SELECT funcionario.nome, SUM(pagamento.valor)\n" +
            "FROM chamado JOIN funcionario ON chamado.id_funcionario = funcionario.id_funcionario\n" +
            "JOIN pagamento ON chamado.id_pagamento = pagamento.id_pagamento\n" +
            "WHERE chamado.status = 'CONCLUIDO' AND funcionario.nome = :nome \n", nativeQuery = true)
    List<Object> qtdPagamentoTotalFunc(String nome);
}
