package web;

/**
 * Created by hhp on 2018/2/9.
 * URI 配置管理
 * Controller层的URL路径常量抽取
 */
//关注点6：为什么要抽取字符串常量
public final class WebURIMappingConstant {
    //系统主页,以后可以做后台登录系统的进入请求
    public static final String URL_HOME = "/home";

    public static final String URL_WEB_ROOT = "/actions/";

    public static final String URL_HOME_LOGOUT = "home/logout";

    public static final String URL_SECURITY_ADMIN_LOGIN ="login/admin";

    public static final String URL_SECURITY_ACCOUNT_LOGIN ="security/accountLogin";

    public static final String URL_SECURITY_ACCOUNT_LOGIN_RQEUQEST = "security/login";

    public static final String URL_SECURITY_ACCOUNT_REGISTER_RQEUQEST = "security/register";

    public static final String URL_SECURITY_ACCOUNT_MANAGER ="accountManager";

    public static final String URL_SECURITY_ACCOUNT_MANAGER_PAGE_QUERY = "accountManager/{page}";

    public static final String URL_SECURITY_ACCOUNT_ADD = "security/addAccount";

    public static final String URL_SECURITY_ACCOUNT_UPDATE = "security/updateAccount/{accountId}";

    public static final String URL_SECURITY_ACCOUNT_RESET_PASS = "security/resetPassword/{accountId}";

    public static final String URL_SECURITY_ACCOUNT_SYSTEM_REGISTER = "security/registerSubmit";

    public static final String URL_SECURITY_ACCOUNT_SYSTEM_RESET_PASSWORD = "security/resetPassword";

    public static final String URL_SECURITY_ACCOUNT_SYSTEM_UPDATE = "security/updateSubmit";

    public static final String URL_SECURITY_ACCOUNT_RESET_PASSWORD_BY_OLD_PASSWORD = "security/resetPasswordByOldPassword";

    public static final String URL_SECURITY_ACCOUNT_SEARCH = "security/account/search";

    //关注点5：SpringMVC处理的真正请求
    public static final String URL_ARTICLE_LIST = "article/list";

    //关注点9：动态路径在SpringMVC中的写法
    public static final String URL_ARTICLE_READ = "article/readArticle/{articleId}";

    public static final String URL_ARTICLE_POSTEDIT_REQUEST = "article/postedit";

    public static final String URL_ARTICLE_INSERT = "article/insert";

    public static final String URL_ARTICLE_MY_BLOG_SPACE ="article/myBlogSpace";

    public static final String URL_ARTICLE_MANAGER = "articleManager";

    public static final String URL_ARTICLE_SEARCH = "article/search";

    public static final String URL_ARTICLE_DELETE = "article/deleteArticle";

    public static final String URL_ARTICLE_VIEW = "article/view";

    public static final String URL_COMMENT_INSERT = "comment/insertComment";

    public static final String URL_COMMENT_MANAGER = "commentManager";

    public static final String URL_COMMENT_DELETE = "comment/deleteComment";

}
