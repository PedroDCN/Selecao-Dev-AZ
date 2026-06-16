package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LoteBO;
import br.com.selecao.locadora.dto.request.LoteRequest;
import br.com.selecao.locadora.dto.response.LoteResponse;
import br.com.selecao.locadora.entity.Lote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lote")
public class LoteService {

    @Autowired
    private LoteBO loteBO;

    @GetMapping
    public ResponseEntity<List<Lote>> buscarTodos() {
        return new ResponseEntity<>(loteBO.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteResponse> buscarPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(loteBO.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LoteResponse> salvarLote(@RequestBody LoteRequest request) {
        return new ResponseEntity<>(loteBO.salvarLote(request), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteResponse> atualizarLote(@PathVariable("id") Long id, @RequestBody LoteRequest request) {
        return new ResponseEntity<>(loteBO.atualizarLote(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LoteResponse> deletarLote(@PathVariable("id") Long id) {
        loteBO.deletarLote(id);
        return ResponseEntity.ok().build();
    }
}
