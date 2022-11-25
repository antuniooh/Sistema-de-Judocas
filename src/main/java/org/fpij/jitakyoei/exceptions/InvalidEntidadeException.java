package org.fpij.jitakyoei.exceptions;

public class InvalidEntidadeException extends Exception {
    public InvalidEntidadeException(String invalidFields) {
        super("Os seguintes dados da entidade estão inválidos:" + invalidFields);
    }
}
