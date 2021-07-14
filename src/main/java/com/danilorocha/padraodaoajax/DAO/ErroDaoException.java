package com.danilorocha.padraodaoajax.DAO;

/**
 *
 * @author Danilo Rocha
 */
public class ErroDaoException extends Exception{

    public ErroDaoException() {
        super("Erro na base de dados");
    }

    public ErroDaoException(String message) {
        super(message);
    }

    public ErroDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErroDaoException(Throwable cause) {
        super(cause);
    }

    public ErroDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
