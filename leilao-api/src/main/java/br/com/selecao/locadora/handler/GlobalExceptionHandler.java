package br.com.selecao.locadora.handler;

import br.com.selecao.locadora.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

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
