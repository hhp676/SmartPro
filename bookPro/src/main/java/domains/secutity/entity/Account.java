package domains.secutity.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by hhp on 2018/3/5.
 * 用户的实体类，Account
 * 对应数据库t_account表
 */
public final class Account {
    private Long id;//主键id

    private String nickname;//此帐号的昵称，比如“苍狼小跟班”

    private String loginName;//登录名，用于与Shiro的user表关联,在数据库中唯一

    private String password;//密码

    private String idCard;//身份证，支持字母

    private String cellPhone;//手机号

    private boolean enabled;//是否可用,0表示冻结违规帐号,1表示是正常帐号

    public Account() {
    }

    public Account(String nickname, String loginName, String password, String idCard, String cellPhone, boolean enabled) {
        this.nickname = nickname;
        this.loginName = loginName;
        this.password = password;
        this.idCard = idCard;
        this.cellPhone = cellPhone;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("nickname", nickname)
                .add("loginName", loginName)
                .add("password", password)
                .add("idCard", idCard)
                .add("cellPhone", cellPhone)
                .add("enabled", enabled)
                .toString();

    }
}
