package br.com.selecao.locadora.exception;

public class LoteNaoEncontradoException extends RuntimeException {
    public LoteNaoEncontradoException(String message) {
        super(message);
    }
}
