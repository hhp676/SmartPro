package domains.secutity;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hhp on 2018/3/27.
 * 用于拦截ajax请求
 * 默认的Shiro配置文件中拦截规则对发送的ajax请求无效
 * 因此必须自己做一个Shiro的过滤器
 * 这样的话，ajax请求也会被Shiro拦截到
 * 本类可以预见到未来将会被复用多次
 * see:http://jinnianshilongnian.iteye.com/blog/2025656
 */
public final class AjaxFilter extends AdviceFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxFilter.class);

    /**
     * 前处理,这里用于判断这此请求是不是ajax请求
     */
    @Override
    protected boolean preHandle(ServletRequest req, ServletResponse resp) throws Exception {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取请求头中的信息，ajax请求最大的特点就是请求头中多了 X-Requested-With 参数
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            LOGGER.info("本次请求是AJAX请求!");
            //现在的用途就是判断当前用户是否被认证
            //先获取到由Web容器管理的subject对象
            Subject subject = SecurityUtils.getSubject();
            //判断是否已经认证
            boolean isAuthc = subject.isAuthenticated();
            if (!isAuthc) {
                LOGGER.info("当前账户使用Shiro认证失败!");
                //如果当前账户没有被认证,本次请求被驳回，ajax进入error的function中进行重定向到登录界面。
                return false;
            }
            return true;
        } else {
            //如果请求类型不是ajax，那么此时requestType为null
            LOGGER.info("非AJAX请求!");
        }
        //默认返回的是true，将本次请求继续执行下去
        return super.preHandle(request, response);
    }

    /**
     * 后处理，类似于AOP中的后置返回增强
     * 在拦截器链执行完成后执行
     * 一般用于记录时间。
     */
    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        super.postHandle(request, response);
    }

    /**
     * 最终处理，一定会执行的，一般用于释放资源。
     * 先留着
     */
    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        super.afterCompletion(request, response, exception);
    }
}
