package com.mmnaseri.cs.qa.monitor.error;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/27/15, 12:34 AM)
 */
public class MonitorFailureException extends RuntimeException {

    public MonitorFailureException(String message) {
        super(message);
    }

    public MonitorFailureException(String message, Throwable cause) {
        super(message, cause);
    }

}
