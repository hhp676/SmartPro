package web.comment;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import domains.article.CommentService;
import domains.article.entity.Comment;
import domains.menu.MenuService;
import domains.menu.entity.Menu;
import web.AbstractController;
import web.JsonResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static web.WebForwardConstant.FWD_COMMENT_VIEW;
import static web.WebURIMappingConstant.*;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by hhp on 2018/3/22.
 */
@Controller
public final class CommentController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private CommentService commentService;
    @Autowired
    private MenuService menuService;

    /**
     * 插入评论
     * 使用ajax从前端获取到json数据封装到comment中
     *
     * @param comment 评论
     * @return Json数据，返回给页面。逻辑控制由页面操作
     */
    @RequestMapping(value = {URL_COMMENT_INSERT}, method = {POST})
    @ResponseBody
    public JsonResponseVO insertComment(HttpSession session, Comment
            comment) {
        LOGGER.info("enter insert commment controller");
        comment.setDate(new Date());
        String name;
        //不情愿写出了try catch,2
        //其实吧，如果有了Shiro框架的认证，怎么会出现空指针异常呢
        //一旦出现无用户登录，只有一种情况：session过期
        //这样看来，try catch还是很有必要的
        try {
            name = getCurrentAccount(session).getNickname();//评论者的名字
        } catch (Exception e) {
            LOGGER.info("当前无用户登录，或者是session过期");
            return new JsonResponseVO(false, "用户可能没有登录");
        }
        comment.setName(name);//设置作者
        LOGGER.info("Controller:insert commment,comment={}", comment);
        int i = commentService.insertComment(comment);
        if (i == 0) {
            return new JsonResponseVO(false);
        }
        return new JsonResponseVO();

    }

    /**
     * 跳转评论页面
     *
     * @return
     */
    @RequestMapping(value = {URL_COMMENT_MANAGER})
    public ModelAndView commentManager(HttpServletRequest request) {
        LOGGER.info("enter commentManager page");
        final Map<String, Object> map = Maps.newHashMap();
        final Map<String, Object> params = Maps.newHashMap();
        final List<Menu> menus = menuService.obtainMenus();
        String str_pageNo = request.getParameter("pageNo");
        if (str_pageNo == null) {
            PageHelper.startPage(1, 13);
        } else {
            PageHelper.startPage(Integer.parseInt(str_pageNo), 13);
        }
        final List<Comment> comments = commentService.getAllComment(params);
        final PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        map.put("menus", menus);
        map.put("comments", comments);
        map.put("pageInfo", pageInfo);
        return new ModelAndView(FWD_COMMENT_VIEW, map);
    }

    @RequestMapping(value = {URL_COMMENT_DELETE})
    public ModelAndView deleteComment(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("delete comment request");
        Long commentId = Long.parseLong(request.getParameter("commentId"));
        Comment comment = commentService.getCommentById(commentId);
        commentService.deleteComment(comment);
        try {
            LOGGER.info("redirect to comment manager page");
            response.sendRedirect(URL_WEB_ROOT + URL_COMMENT_MANAGER);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();

    }
}
