package abyss.plugin.api;

public class AbyssRuntimeException extends RuntimeException {

    AbyssRuntimeException() {
    }

    AbyssRuntimeException(String message) {
        super(message);
    }

    AbyssRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    AbyssRuntimeException(Throwable cause) {
        super(cause);
    }

    AbyssRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
