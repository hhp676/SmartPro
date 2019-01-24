package web;

/**
 * Created by hhp on 2/7/2018.
 *
 * 配置统一的页面跳转路径
 */
public final class WebForwardConstant {
    private WebForwardConstant() {
    }

    public static final String FWD_SECURITY_SYSTEM_HOME ="home";

    public static final String FWD_SECURITY_LOGIN_FAIL ="loginFail";

    public static final String FWD_SECURITY_SYSTEM_INDEX = "system_index";

    public static final String FWD_SECURITY_ACCOUNT_MANAGER = "security/accountManager";

    public static final String FWD_SECURITY_ACCOUNT_SEARCH_RESULT = "security/search";

    public static final String FWD_SECURITY_ACCOUNT_ADD = "security/addAccount";

    public static final String FWD_SECURITY_ACCOUNT_UPDATE = "security/updateAccount";

    public static final String FWD_SECURITY_ACCOUNT_RESET_PASS = "security/resetPassword";

    public static final String FWD_SECURITY_ACCOUNT_LOGIN = "security/login";

    public static final String FWD_SECURITY_ACCOUNT_REGISTER = "security/register";

    public static final String FWD_ARTICLE_LIST_HOME ="new/main"; // "article/list";

    public static final String FWD_ARTICLE_DETAIL = "new/articleDetail"; //"article/articleDetail";

    public static final String FWD_ARTICLE_POSTEDIT = "article/postedit";

    public static final String FWD_ARTICLE_BLOG_SPACE = "article/myBlogSpace";

    public static final String FWD_ARTICLE_MANAGER = "article/articleManager";

    public static final String FWD_ARTICLE_SEARCH = "article/search";

    public static final String FWD_ARTICLE_VIEW = "article/view";

    public static final String FWD_COMMENT_VIEW = "comment/view";

    public static final String FWD_FAIL_PAGE = "fail";

    public static final String FWD_SUCCESS_PAGE = "success";
}
