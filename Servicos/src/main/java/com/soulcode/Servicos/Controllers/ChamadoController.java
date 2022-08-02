package com.soulcode.Servicos.Controllers;

import com.soulcode.Servicos.Models.Chamado;
import com.soulcode.Servicos.Services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class ChamadoController {

    @Autowired
    ChamadoService chamadoService;

    @GetMapping("/chamados")
    public List<Chamado> mostrarTodosChamados(){
        List<Chamado> chamados = chamadoService.mostrarTodosChamados();
        return chamados;
    }

    @GetMapping("/chamados/chamados-por-func")
    public List<Object> buscarQtdChamadosPorFuncionario() {
        List<Object> chamados = chamadoService.buscarQtdChamadosPorFuncionario();
        return chamados;
    }

    @GetMapping("/chamados/total-pago-cliente")
    public List<Object> buscarTotalClientePago(@RequestParam("nome") String nome){
        List<Object> totalPagamento = chamadoService.buscarTotalClientePago(nome);
        return totalPagamento;
    }

    @GetMapping("/chamados/{idChamado}")
    public ResponseEntity<Chamado> buscarUmChamado(@PathVariable Integer idChamado){
        Chamado chamado  = chamadoService.mostrarUmChamado(idChamado);
        return ResponseEntity.ok().body(chamado);
    }

    @GetMapping("/chamados/pelo-cliente/{idCliente}")
    public List<Chamado> buscarChamadosPeloCliente(@PathVariable Integer idCliente){
        List<Chamado> chamados = chamadoService.buscarChamadosPeloCliente(idCliente);
        return chamados;
    }

    @GetMapping("/chamados/pelo-funcionario/{idFuncionario}")
    public List<Chamado> buscarChamadosPeloFuncionario(@PathVariable Integer idFuncionario){
        List<Chamado> chamados = chamadoService.buscarChamadosPeloFuncionario(idFuncionario);
        return chamados;
    }

    @GetMapping("/chamados/pelo-status")
    public List<Chamado> buscarChamadosPeloStatus(@RequestParam("status") String status){
        List<Chamado> chamados = chamadoService.buscarChamadosPeloStatus(status);
        return chamados;
    }

    @GetMapping("/chamados/por-intervalo-data")
    public List<Chamado> buscarPorIntervaloData(@RequestParam("data1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data1,
                                                @RequestParam("data2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data2){
        List<Chamado> chamados = chamadoService.buscarPorIntervaloData(data1,data2);
        return chamados;
    }

    @GetMapping("/chamados/qtd-por-status")
    public Object buscarQtdChamadosPorStatus(){
        Object chamados = chamadoService.buscarQtdChamadosPorStatus();
        return chamados;
    }

    @GetMapping("/chamados/pagamento-quitado")
    public List<Chamado> buscarChamadosPeloPagamentoStatusQuitado(){
        List<Chamado> chamados = chamadoService.buscarChamadosPeloPagamentoStatusQuitado();
        return chamados;
    }

    @GetMapping("/chamados/pagamento-lancado")
    public List<Chamado> buscarChamadosPeloPagamentoStatusLancado(){
        List<Chamado> chamados = chamadoService.buscarChamadosPeloPagamentoStatusLancado();
        return chamados;
    }

    @PostMapping("/chamados/{idCliente}")
    public ResponseEntity<Chamado> cadastrarChamado(@PathVariable Integer idCliente,
                                                    @RequestBody Chamado chamado){
        chamado = chamadoService.cadastrarChamado(chamado,idCliente);
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(chamado.getIdChamado()).toUri();
        return ResponseEntity.created(novaUri).body(chamado);
    }

    @DeleteMapping("/chamados/{idChamado}")
       public ResponseEntity<Void> excluirChamado(@PathVariable Integer idChamado){
        chamadoService.excluirChamado(idChamado);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/chamados/{idChamado}")
    public ResponseEntity<Chamado> editarChamado(@PathVariable Integer idChamado,
                                                 @RequestBody Chamado chamado){
        chamado.setIdChamado(idChamado);
        chamadoService.editarChamado(chamado, idChamado);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/chamados/atribuir-funcionario/{idChamado}/{idFuncionario}")
    public ResponseEntity<Chamado> atribuirFuncionario(@PathVariable Integer idChamado,
                                                       @PathVariable Integer idFuncionario){
        chamadoService.atribuirFuncionario(idChamado, idFuncionario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/chamados/modificar-status/{idChamado}")
    public ResponseEntity<Chamado> modificarStatus(@PathVariable Integer idChamado,
                                                   @RequestParam("status") String status){
        chamadoService.modificarStatus(idChamado,status);
        return ResponseEntity.noContent().build();
    }

}
