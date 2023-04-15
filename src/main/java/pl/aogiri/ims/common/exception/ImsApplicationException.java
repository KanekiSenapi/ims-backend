package pl.aogiri.ims.common.exception;

public abstract class ImsApplicationException extends RuntimeException {

    public ImsApplicationException(String message) {
        super(message);
    }
}
