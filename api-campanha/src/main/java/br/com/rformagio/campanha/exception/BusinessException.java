package br.com.rformagio.campanha.exception;

public class BusinessException extends Exception {

    public BusinessException(Exception e){
        super(e);
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, Exception e){
        super(message, e);
    }


}
