package com.hrs.test.util;

import java.text.MessageFormat;

public class ServiceException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ServiceException(Throwable exception) {
        super(exception);
    }

    public ServiceException(String message) {
        super(message);
    }

    /**
     * ServiceException
     *
     * @param msgPattern   The message pattern string
     * @param msgArguments The arguments to the message
     * @see java.text.MessageFormat
     */
    public ServiceException(String msgPattern, Object... msgArguments) {
        this(MessageFormat.format(msgPattern, msgArguments));
    }

}
