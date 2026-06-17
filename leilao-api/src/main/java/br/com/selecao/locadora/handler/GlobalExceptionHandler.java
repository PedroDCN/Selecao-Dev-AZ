package br.com.selecao.locadora.handler;

import br.com.selecao.locadora.exception.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof ConstraintViolationException) {
            String constrainName = ((ConstraintViolationException) cause).getConstraintName();
            String specificCause = handleSpecificConstrain(constrainName);
            return new ResponseEntity<>(specificCause, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private String handleSpecificConstrain(String constrainName) {
        if (constrainName == null) return "Erro no banco de dados desconhecido";
        switch (constrainName) {
            case "empresa_leilao_fk":
                return "Não pode deletar empresa: empresa possui leilões ativos";
            case "empresa_comp_fk":
                return "Não pode deletar empresa: empresa participa de leilões";
            case "leilao_comp_fk":
                return "Não pode deletar leilão: empresa(s) participa(m) de leilão";
            case "leilao_lote_fk":
                return "Não pode deletar leilão: leilão possui lotes";
            case "unidade_lote_fk":
                return "Não pode deletar lote: unidade sendo usada por lote";
            default:
                return "Erro no banco de dados desconhecido: " + constrainName;
        }
    }

    @ExceptionHandler(CompradorInvalidoException.class)
    public ResponseEntity<String> handleCompradorInvalidoException(CompradorInvalidoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompradorNaoEncontradoException.class)
    public ResponseEntity<String> handleCompradorNaoEncontradoException(CompradorNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoteInvalidoException.class)
    public ResponseEntity<String> handleLoteInvalidoException(LoteInvalidoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoteNaoEncontradoException.class)
    public ResponseEntity<String> handleLoteNaoEncontradoException(LoteNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LeilaoInvalidoException.class)
    public ResponseEntity<String> handleLeilaoInvalidoException(LeilaoInvalidoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LeilaoNaoEncontradoException.class)
    public ResponseEntity<String> handleLeilaoNaoEncontradoException(LeilaoNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmpresaNaoEncontradaException.class)
    public ResponseEntity<String> handleEmpresaNaoEncontradaException(EmpresaNaoEncontradaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmpresaInvalidaException.class)
    public ResponseEntity<String> handleEmpresaInvalidaException(EmpresaInvalidaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnidadeInvalidaException.class)
    public ResponseEntity<String> handleUnidadeInvalida(UnidadeInvalidaException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnidadeNaoEncontradaException.class)
    public ResponseEntity<String> handleUnidadeNaoEncontrada(UnidadeNaoEncontradaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeral(Exception ex) {
//        ex.printStackTrace();
        return new ResponseEntity<>("Um erro inesperado aconteceu", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
