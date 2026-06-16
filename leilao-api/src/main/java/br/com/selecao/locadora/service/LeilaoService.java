package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LeilaoBO;
import br.com.selecao.locadora.dto.request.LeilaoRequest;
import br.com.selecao.locadora.dto.response.LeilaoListResponse;
import br.com.selecao.locadora.dto.response.LeilaoResponse;
import br.com.selecao.locadora.entity.Leilao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/leilao")
public class LeilaoService {

    @Autowired
    private LeilaoBO leilaoBO;

    @GetMapping
    public ResponseEntity<List<LeilaoListResponse>> buscarTodos() {
        return new ResponseEntity<>(leilaoBO.buscarTodosParaListagem(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeilaoResponse> buscarPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(leilaoBO.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LeilaoResponse> salvarLeilao(@RequestBody LeilaoRequest request) {
        return new ResponseEntity<>(leilaoBO.salvarLeilao(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeilaoResponse> atualizarLeilao(@PathVariable("id") Long id, @RequestBody LeilaoRequest request) {
        return new ResponseEntity<>(leilaoBO.atualizarLeilao(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLeilao(@PathVariable("id") Long id) {
        leilaoBO.deletarLeilao(id);
        return ResponseEntity.ok().build();
    }
}
