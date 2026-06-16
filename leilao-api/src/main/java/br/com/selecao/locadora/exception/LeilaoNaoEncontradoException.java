package br.com.selecao.locadora.exception;

public class LeilaoNaoEncontradoException extends RuntimeException {
    public LeilaoNaoEncontradoException(String message) {
        super(message);
    }
}
