package web;

import domains.secutity.entity.Account;
;import javax.servlet.http.HttpSession;

/**
 * Created by hhp on 2018/3/22.
 * time: 2018-03-22 13:58:04
 * 定义Controller的超类
 */
public abstract class AbstractController {
    /**
     * 获取当前session下的登录的用户
     * 滥用异常可能会将系统带来额外的风险
     * 说实话，用到异常，我都感觉很不爽
     * 在实际项目中几乎见不到异常。
     * 虽然在接口中预定义异常可以为项目未来扩展做准备
     * 但是代价就是在代码中不得不处理异常
     * 如果是运行时异常,一旦出错，找都找不到，只知道
     * 在发送ajax请求的时候发现系统内部500错误
     * 比如在刚才测试的时候，一直报500错误，原因是我没有登录。
     * getCurrentAccount()方法抛出异常
     * 但是因为是异步请求，在页面什么原因都不知道
     *
     * @param session 会话
     * @return 当前登录的用户
     */
    public Account getCurrentAccount(HttpSession session) {
        final Account account = (Account) session.getAttribute("account");
        if (account == null) {
            throw new RuntimeException(new NullPointerException("当前无登录用户"));
        }
        return account;
    }
}
