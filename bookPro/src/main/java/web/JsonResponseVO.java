package web;

import static com.google.common.base.Objects.toStringHelper;

/**
 * Created by IntelliJ IDEA.
 *
 * 作为server返回给client的JSON格式信息
 *
 */
public final class JsonResponseVO {
    private boolean success;
    private String reason;
    private String other;

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public JsonResponseVO(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }

    public JsonResponseVO(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JsonResponseVO() {
        this.success = true;
        this.reason = "ok";
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("success", success)
                .add("reason", reason)
                .add("other", other)
                .toString();
    }
}
