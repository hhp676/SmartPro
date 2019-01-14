package code.exception;


/**
 * desc: 端到端默认异常类<br />
 * Date: 2017/9/15 <br/>
 *
 * @author qiujingde
 */
public class EteException extends RuntimeException {
    private static final long serialVersionUID = 2829028205187653924L;

    public EteException(String msg) {
        super(msg);
        logStackTrace();
    }

    public EteException(Exception cause) {
        super(cause);
        logStackTrace();
    }

    public EteException(String msg, Exception cause) {
        super(msg, cause);
        logStackTrace();
    }

    private void logStackTrace() {
        logStackTrace(getMessage(), this);
    }

    private void logStackTrace(String msg, Throwable t) {
        if (t.getCause() != null) {
            logStackTrace(t.getMessage(), t.getCause());
        }
    }

}
