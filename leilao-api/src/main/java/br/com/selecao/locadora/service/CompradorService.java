package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.CompradorBO;
import br.com.selecao.locadora.dto.request.CompradorRequest;
import br.com.selecao.locadora.dto.response.CompradorResponse;
import br.com.selecao.locadora.entity.Comprador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comprador")
public class CompradorService {

    @Autowired
    private CompradorBO compradorBO;

    @GetMapping
    public ResponseEntity<List<Comprador>> buscaTodos() {
        return new ResponseEntity<>(compradorBO.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{idEmpresa}/{idLeilao}")
    public ResponseEntity<CompradorResponse> buscaPorId(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable("idLeilao") Long idLeilao) {
        return new ResponseEntity<>(compradorBO.buscarPorId(idEmpresa, idLeilao), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompradorResponse> salvarComprador(@RequestBody CompradorRequest request) {
        return new ResponseEntity<>(compradorBO.salvarComprador(request), HttpStatus.CREATED);
    }

    @PutMapping("/{idEmpresa}/{idLeilao}")
    public ResponseEntity<CompradorResponse> atualizarComprador(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable("idLeilao") Long idLeilao, @RequestBody CompradorRequest request) {
        return new ResponseEntity<>(compradorBO.atualizarComprador(idEmpresa, idLeilao, request), HttpStatus.OK);
    }

    @DeleteMapping("/{idEmpresa}/{idLeilao}")
    public ResponseEntity<?> deletarComprador(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable("idLeilao") Long idLeilao) {
        compradorBO.deletarComprador(idEmpresa, idLeilao);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
