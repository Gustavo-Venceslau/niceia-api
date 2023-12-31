package com.galmv.user.exceptions;

import java.io.Serial;

public class UserAlreadyExistsException extends RuntimeException{

    @Serial
    private final static long serialVersionUID = 1L;
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
