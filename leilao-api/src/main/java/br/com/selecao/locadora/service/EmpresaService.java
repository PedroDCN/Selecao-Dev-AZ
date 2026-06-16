package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.EmpresaBO;
import br.com.selecao.locadora.dto.request.EmpresaRequest;
import br.com.selecao.locadora.dto.response.EmpresaResponse;
import br.com.selecao.locadora.entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaService {

    @Autowired
    private EmpresaBO empresaBO;

    @GetMapping
    public ResponseEntity<List<Empresa>> buscarTodos() {
        return new ResponseEntity<>(empresaBO.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponse> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(empresaBO.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping("/cnpj")
    public ResponseEntity<EmpresaResponse> buscarPorCnpj(@RequestParam String cnpj) {
        return new ResponseEntity<>(empresaBO.buscarPorCnpj(cnpj), HttpStatus.OK);
    }

    @GetMapping("/usuario")
    public ResponseEntity<EmpresaResponse> buscarPorUsuario(@RequestParam String usuario) {
        return new ResponseEntity<>(empresaBO.buscarPorUsuario(usuario), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmpresaResponse> salvarEmpresa(@RequestBody EmpresaRequest request) {
        return new ResponseEntity<>(empresaBO.salvarEmpresa(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponse> atualizarEmpresa(@PathVariable("id") Long id,@RequestBody EmpresaRequest request) {
        return new ResponseEntity<>(empresaBO.atualizarEmpresa(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaResponse> deletarEmpresa(@PathVariable("id") Long id) {
        empresaBO.deletarEmpresa(id);
        return ResponseEntity.ok().build();
    }
}
