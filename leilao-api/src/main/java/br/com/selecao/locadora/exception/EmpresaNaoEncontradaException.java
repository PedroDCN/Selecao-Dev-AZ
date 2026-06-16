package br.com.selecao.locadora.exception;

public class EmpresaNaoEncontradaException extends RuntimeException {
    public EmpresaNaoEncontradaException(String message) {
        super(message);
    }
}
