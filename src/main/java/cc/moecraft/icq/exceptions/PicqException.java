package cc.moecraft.icq.exceptions;

public abstract class PicqException extends RuntimeException {
    public PicqException() {
        super();
    }

    public PicqException(String reason) {
        super(reason);
    }
}
