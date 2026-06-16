package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.UnidadeBO;
import br.com.selecao.locadora.dto.request.UnidadeRequest;
import br.com.selecao.locadora.dto.response.UnidadeResponse;
import br.com.selecao.locadora.entity.Unidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/unidade")
public class UnidadeService {

    @Autowired
    private UnidadeBO unidadeBO;

    @GetMapping
    public ResponseEntity<List<Unidade>> buscarTodos() {
        return new ResponseEntity<>(unidadeBO.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponse> buscarPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(unidadeBO.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UnidadeResponse> salvarUnidade(@RequestBody UnidadeRequest request) {
        return new ResponseEntity<>(unidadeBO.salvarUnidade(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeResponse> atualizarUnidade(@PathVariable("id") Long id, @RequestBody UnidadeRequest request) {
        return new ResponseEntity<>(unidadeBO.atualizarUnidade(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUnidade(@PathVariable Long id) {
        unidadeBO.deletarUnidade(id);
        return ResponseEntity.ok().build();
    }
}