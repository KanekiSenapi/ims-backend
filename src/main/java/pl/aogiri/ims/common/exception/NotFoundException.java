package pl.aogiri.ims.common.exception;

public class NotFoundException extends ImsApplicationException {

    public NotFoundException() {
        super(ErrorMessages.NOT_FOUND);
    }
}
