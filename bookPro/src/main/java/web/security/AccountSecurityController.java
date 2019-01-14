package web.security;

/**
 * Created by hhp on 2018/3/8.
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import domains.menu.MenuService;
import domains.menu.entity.Menu;
import domains.secutity.SecurityService;
import domains.secutity.entity.Account;
import web.JsonResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static web.WebForwardConstant.*;
import static web.WebURIMappingConstant.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public final class AccountSecurityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountSecurityController.class);
    @Autowired
    private SecurityService securityService;
    @Autowired
    private MenuService menuService;

    /**
     * 后台管理员登录
     *
     * @param session
     * @param loginName 登录名
     * @param password  密码
     * @return 登录成功跳转后台首页，失败跳转登录失败页面
     */
    @RequestMapping(value = {URL_SECURITY_ADMIN_LOGIN}, method = {POST})
    public ModelAndView accountAuthenticate(HttpSession session, @RequestParam("loginName") String loginName, @RequestParam("password") String password) {
        LOGGER.info("Controller:accountAuthenticate,loginName={},password={}", loginName, password);
        if (!loginName.equals("admin")) {
            LOGGER.info("非管理员登录");
            return new ModelAndView(FWD_SECURITY_LOGIN_FAIL);
        }
        final Account account = securityService.accountAuthenticate(loginName, password);
        if (account.getId() == null) {
            LOGGER.info("后台登录失败");
            return new ModelAndView(FWD_SECURITY_LOGIN_FAIL);
        }
        final Map<String, Object> map = Maps.newHashMap();
        final List<Menu> menus = menuService.obtainMenus();
        map.put("account", account);
        map.put("menus", menus);
        session.setAttribute("account", account);
        LOGGER.info("enter system index page,with account={}", account);
        LOGGER.info("enter system index page,with menus={}", menus);
        return new ModelAndView(FWD_SECURITY_SYSTEM_INDEX, map);
    }

    /**
     * 跳转用户注册页面
     */
    @RequestMapping({URL_SECURITY_ACCOUNT_REGISTER_RQEUQEST})
    public ModelAndView fwdRegister() {
        LOGGER.info("enter Account Register page");
        return new ModelAndView(FWD_SECURITY_ACCOUNT_REGISTER);
    }

    /**
     * 跳转用户登录页面
     */
    @RequestMapping({URL_SECURITY_ACCOUNT_LOGIN_RQEUQEST})
    public ModelAndView fwdLogin() {
        LOGGER.info("enter Account Login page");
        return new ModelAndView(FWD_SECURITY_ACCOUNT_LOGIN);
    }

    /**
     * 用户登录数据提交
     *
     * @param session
     * @param loginName 登录名
     * @param password  密码
     * @return 登录成功跳转博客空间首页，失败弹窗提示
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_LOGIN}, method = {POST})
    //关注点13：使用@ResponseBody将JsonResponseVO对象解析成Json数据返回给前端
    @ResponseBody
    //关注点12：使用@RequestParam("loginName")解析前端发送的loginName变量的值
    public JsonResponseVO accountLogin(HttpSession session, @RequestParam("loginName") String loginName, @RequestParam("password") String password) {
        LOGGER.info("Controller:accountLogin,loginName={},password={}", loginName, password);
        final Account account = securityService.accountAuthenticate(loginName, password);
        if (account.getId() == null) {
            LOGGER.info("用户登录失败");
            JsonResponseVO VO = new JsonResponseVO(false);
            VO.setOther("帐号错误或者密码错误");
            return VO;
        }
        if (!account.isEnabled()) {
            LOGGER.info("帐号不可用");
            JsonResponseVO VO = new JsonResponseVO(false);
            VO.setOther("帐号已经被冻结，请联系管理员!");
            return VO;
        }
        session.setAttribute("account", account);
        //关注点13:最终返回一个JsonReponseVO对象，作为ajax异步请求的响应
        return new JsonResponseVO();
    }


    /**
     * 去用户管理页面
     * 携带的参数有:menus,accounts
     * accounts经过了基本的分页查询,每页显示X条数据
     *
     * @return 携带上菜单数据，用户数据，去用户管理页面
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_MANAGER})
    public ModelAndView accountManager() {
        LOGGER.info("enter accountManager page");
        final Map<String, Object> param = Maps.newHashMap();
        final Map<String, Object> map = Maps.newHashMap();
        //菜单数据
        final List<Menu> menus = menuService.obtainMenus();
        map.put("menus", menus);
        //账户数据,每页显示X条数据
        PageHelper.startPage(1, 4);
        final List<Account> accounts = securityService.obtainAllAccount(param);
        final PageInfo<Account> pageInfo = new PageInfo<>(accounts);
        map.put("accounts", accounts);
        map.put("pageInfo", pageInfo);
        LOGGER.info("enter account manager page");
        LOGGER.info("Account size = {}", accounts.size());
        LOGGER.info("Actually total account size ={}", pageInfo.getTotal());
        return new ModelAndView(FWD_SECURITY_ACCOUNT_MANAGER, map);
    }

    /**
     * 分页查询Account数据
     * 在URL路径上携带了应该显示第几页的参数。每页显示X条数据
     *
     * @param page 第几页的数据
     * @return 跳转帐号管理页面
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_MANAGER_PAGE_QUERY})
    public ModelAndView pageQuery(@PathVariable("page") Integer page) {
        LOGGER.info("Controller:enter accountManager page");
        final Map<String, Object> param = Maps.newHashMap();
        final Map<String, Object> map = Maps.newHashMap();
        //菜单数据
        final List<Menu> menus = menuService.obtainMenus();
        map.put("menus", menus);
        //账户数据
        PageHelper.startPage(page, 4);
        final List<Account> accounts = securityService.obtainAllAccount(param);
        final PageInfo<Account> pageInfo = new PageInfo<>(accounts);
        map.put("accounts", accounts);
        map.put("pageInfo", pageInfo);
        LOGGER.info("enter account manager page");
        LOGGER.info("Account size = {}", accounts.size());
        LOGGER.info("Actually total account size ={}", pageInfo.getTotal());
        return new ModelAndView(FWD_SECURITY_ACCOUNT_MANAGER, map);
    }

    /**
     * 跳转添加账户页面
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_ADD})
    public ModelAndView addAccount() {
        LOGGER.info("ready fwd addAccount Page !");
        final Map<String, Object> map = Maps.newHashMap();
        //菜单数据
        final List<Menu> menus = menuService.obtainMenus();
        map.put("menus", menus);
        return new ModelAndView(FWD_SECURITY_ACCOUNT_ADD, map);
    }

    /**
     * 跳转修改账户页面
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_UPDATE})
    public ModelAndView updateAccount(@PathVariable("accountId") Integer accountId) {
        LOGGER.info("ready fwd updateAccount Page !");
        final Account account = securityService.getAccountById(accountId);
        final Map<String, Object> map = Maps.newHashMap();
        //菜单数据
        final List<Menu> menus = menuService.obtainMenus();
        map.put("menus", menus);
        map.put("account", account);
        return new ModelAndView(FWD_SECURITY_ACCOUNT_UPDATE, map);
    }

    /**
     * 从前端接收到ajax请求,插入account实体到数据库
     *
     * @param account 来自前端，由JSON数据封装
     * @return 是否插入成功，返回Json格式的JsonRepsonseVO对象。
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_SYSTEM_REGISTER}, method = {POST})
    @ResponseBody
    public JsonResponseVO registerSubmit(Account account) {
        LOGGER.info("receive from system register Account,Account_Json={}", account);
        int i = securityService.insertAccount(account);
        if (i == 0) {
            LOGGER.info("插入数据失败");
            JsonResponseVO VO = new JsonResponseVO(false);
            VO.setOther("用户昵称重复或者登陆账号重复");
            return VO;
        }
        /**
         * 目标是跳转到插入的最后一条记录的位置,仅仅在插入的操作做，其它操作不想弄，有点麻烦,还要查询一次数据库。
         * 步骤还是要获取到那个页面的menus,accounts。
         * 先弄到所有的记录数，然后算出应该跳转第几页page
         * 把这个插入的数据在第几页返回给前台。
         * ajax的success如果获取到数据，那么就发送去这个页面的请求
         * 请求路径是  actions/accountManager/{page}
         * 现在规定每页4跳数据，如果要换的话，在本页面搜索4即可
         */
        final Map<String, Object> param = Maps.newHashMap();
        final Map<String, Object> map = Maps.newHashMap();
        //菜单数据
        final List<Menu> menus = menuService.obtainMenus();
        map.put("menus", menus);
        //获取所有帐号，包括刚才才插入的
        final List<Account> accounts = securityService.obtainAllAccount(param);
        final int totalCount = accounts.size();
        final int page;
        //算出本次插入的数据在第几页
        if (totalCount % 4 == 0) {
            page = totalCount / 4;
        } else {
            page = (totalCount / 4) + 1;
        }
        final JsonResponseVO VO = new JsonResponseVO();
        //将第几页封装到VO中
        VO.setOther(String.valueOf(page));
        return VO;
    }

    /**
     * 从前端接收到ajax请求,将更新后的account实体到数据库
     *
     * @param account 来自前端，由JSON数据封装
     * @return 是否更新成功，返回Json格式的JsonRepsonseVO对象。
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_SYSTEM_UPDATE}, method = {POST})
    @ResponseBody
    public JsonResponseVO updatesubmit(Account account, HttpSession session) {
        LOGGER.info("receive from system update Account,Account_Json={}", account);
        int i = securityService.updateAccount(account);
        if (i == 0) {
            LOGGER.info("更新数据失败");
            return new JsonResponseVO(false);
        }
        final JsonResponseVO VO = new JsonResponseVO();
        return VO;
    }

    /**
     * 跳转重置账户密码页面
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_RESET_PASS})
    public ModelAndView resetPassword(@PathVariable("accountId") Integer accountId) {
        LOGGER.info("ready fwd resetPassword Page !");
        final Account account = securityService.getAccountById(accountId);
        final Map<String, Object> map = Maps.newHashMap();
        //菜单数据
        final List<Menu> menus = menuService.obtainMenus();
        map.put("menus", menus);
        map.put("account", account);
        return new ModelAndView(FWD_SECURITY_ACCOUNT_RESET_PASS, map);
    }

    /**
     * 接收从页面的ajax请求，获取到用户输入的密码
     *
     * @param password  用户输入的新密码
     * @param accountId 账户的ID
     * @return 封装着是否操作成功的JSON数据
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_SYSTEM_RESET_PASSWORD}, method = {POST})
    @ResponseBody
    public JsonResponseVO resetPasswordsubmit(@RequestParam("password") String password, @RequestParam("accountId") Integer accountId) {
        LOGGER.info("receive from system resetPassword_submit,password={},accountId={}", password, accountId);
        int i = securityService.resetPassword(accountId, password);
        if (i == 0) {
            LOGGER.info("重置密码失败");
            return new JsonResponseVO(false);
        }
        final JsonResponseVO VO = new JsonResponseVO();
        return VO;
    }

    /**
     * 在博客空间里面发送修改密码的操作，需要输入旧的密码
     *
     * @param loginName   登录名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param response
     * @return 是否修改成功锁跳转的页面
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_RESET_PASSWORD_BY_OLD_PASSWORD}, method = {POST})
    public ModelAndView resetPasswordByOldPassword(@RequestParam("loginName") String loginName, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
                                                   HttpServletResponse response) {
        LOGGER.info("resetPasswordByOldPassword,oldPassword={},newPassword={}", oldPassword, newPassword);

        int i = securityService.updatePassword(loginName, oldPassword, newPassword);
        if (i == 0) {
            return new ModelAndView(FWD_FAIL_PAGE);
        }
        try {
            response.sendRedirect("/");
        } catch (Exception e) {
            LOGGER.info("转发出现一些问题");
        }
        return new ModelAndView();
    }

    /**
     * 根据指定的情况查询相应的用户账户
     * 从后台管理发送出来的请求
     * SQL值得研究哦
     */
    @RequestMapping(value = {URL_SECURITY_ACCOUNT_SEARCH}, method = {GET, POST})
    public ModelAndView accountSearch(@RequestParam("conditionValue") String conditionValue, @RequestParam("searchValue") String searchValue,
                                      @RequestParam("enabledValue") String enabledValue) {
        LOGGER.info("conditionValue={}", conditionValue);
        LOGGER.info("searchValue={}", searchValue);
        LOGGER.info("enabledValue={}", enabledValue);
        Map<String, Object> params = Maps.newHashMap();
        params.put("condition", conditionValue);
        params.put("searchValue", searchValue);
        params.put("enabled", enabledValue);
        final Map<String, Object> map = Maps.newHashMap();
        //菜单数据
        final List<Menu> menus = menuService.obtainMenus();
        //根据指定的情况搜索用户
        final List<Account> accounts = securityService.getAccountByCondition(params);
        map.put("menus", menus);
        map.put("accounts", accounts);
        return new ModelAndView(FWD_SECURITY_ACCOUNT_SEARCH_RESULT, map);
    }


}
