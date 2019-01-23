package web.home;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.org.lidalia.slf4jext.Logger;
import uk.org.lidalia.slf4jext.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static web.WebForwardConstant.FWD_SECURITY_SYSTEM_HOME;
import static web.WebURIMappingConstant.URL_HOME;
import static web.WebURIMappingConstant.URL_HOME_LOGOUT;

/*import static org.springframework.web.bind.annotation.RequestMethod.GET;*/

/**
 * Created by hhp on 2018/3/8.
 */
@Controller
public final class HomePageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);

    /**
     * 进入后台管理系统主页
     *
     * @return
     */
    @RequestMapping(value = {URL_HOME}, method = {})
    public ModelAndView home() {
        LOGGER.info("enter home page");
        return new ModelAndView(FWD_SECURITY_SYSTEM_HOME);
    }

    /**
     * 用户退出
     */
    @RequestMapping(value = {URL_HOME_LOGOUT})
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        LOGGER.info("account logout");
        session.removeAttribute("account");
        LOGGER.info("account={}", session.getAttribute("account"));
        response.sendRedirect("/actions/article/list");
    }

}
