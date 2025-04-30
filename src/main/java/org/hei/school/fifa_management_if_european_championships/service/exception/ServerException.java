package org.hei.school.fifa_management_if_european_championships.service.exception;

public class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }
    public ServerException(Exception e) {
        super(e);
    }
}
