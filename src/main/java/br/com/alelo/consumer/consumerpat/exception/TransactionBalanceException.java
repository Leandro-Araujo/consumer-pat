package br.com.alelo.consumer.consumerpat.exception;

public class TransactionBalanceException extends RuntimeException{
    public TransactionBalanceException(String message) {
        super(message);
    }
}
