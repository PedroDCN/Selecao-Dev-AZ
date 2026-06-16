package br.com.selecao.locadora.exception;

public class LoteInvalidoException extends RuntimeException {
    public LoteInvalidoException(String message) {
        super(message);
    }
}
