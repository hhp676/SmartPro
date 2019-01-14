package web.article;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import domains.article.ArticleService;
import domains.article.CategoryService;
import domains.article.CommentService;
import domains.article.entity.*;
import domains.menu.MenuService;
import domains.menu.entity.Menu;
import domains.secutity.entity.Account;
import web.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static web.WebForwardConstant.*;
import static web.WebURIMappingConstant.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by hhp on 2018/3/19.
 */
@Controller
public final class ArticleController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MenuService menuService;

    /**
     * 去所有文章展示页面，这个是去博客空间的主页
     *
     * @return 携带上文章的参数，去文章首页
     */
    @RequestMapping(value = {URL_ARTICLE_LIST}, method = {GET})
    public ModelAndView home(HttpServletRequest request) {
        LOGGER.info("enter article list page");
        //获取点击量最高的X篇文章
        final List<Article> hotArticles = articleService.getArticleByClick(5);
        LOGGER.info("page query for article");
        //拿到用户需要的是哪一页的文章数据
        String str_pageNo = request.getParameter("pageNo");
        if (str_pageNo == null) {
            PageHelper.startPage(1, 8);
        } else {
            PageHelper.startPage(Integer.parseInt(str_pageNo), 8);
        }
        //封装数据的map集合
        final Map<String, Object> map = Maps.newHashMap();
        //获取所有作者的所有文章
        final List<Article> articles = articleService.getAllArticle(map);
        final PageInfo<Article> pageInfo = new PageInfo<>(articles);
        LOGGER.info("actually total size = {}", pageInfo.getTotal());
        //处理时间格式,突然发现数据库设计不好，坑的就是代码
        final int size = articles.size();
        LOGGER.info("get Comment size for every Article");
        for (int i = 0; i < size; i++) {
            Article article = articles.get(i);
            //处理时间
            handleTime(article);
            //处理评论，根据文章获取此文章的评论,突然感觉好消耗性能
            List<Comment> comments = commentService.getCommentsByArticle(article);
            article.setComments(comments);
        }
        //获取推荐的文章的id
        final List<Long> articleIds = articleService.getRecommandArticleId();
        //获取推荐的文章
        final List<Article> recommandArticles = articleService.getRecommandArtice(articleIds);
        map.put("articles", articles);
        map.put("hotArticles", hotArticles);
        map.put("pageInfo", pageInfo);
        map.put("recommandArticles", recommandArticles);
        //关注点7：为JSP页面提供用于渲染的数据
        return new ModelAndView(FWD_ARTICLE_LIST_HOME, map);
    }

    /**
     * 去文章详情页面
     * 根据URL路径中指定的文章ID号，去获取制定文章的内容
     *
     * @param articleId 指定的文章的ID号
     * @return 获取此文章的数据，并去文章详情页面
     */
    @RequestMapping(value = {URL_ARTICLE_READ})
    //关注点10：解析动态参数
    public ModelAndView readArticle(@PathVariable("articleId") Long articleId) {
        LOGGER.info("enter article detail page, articleId = {}", articleId);
        final Article article = articleService.getArticleById(articleId);
        //点击量增加1
        Long click = article.getClick();
        article.setClick(++click);
        int i = articleService.updateArticle(article);
        if (i == 1) {
            LOGGER.info("点击量刷新，现在的点击量是={}", click);
        }
        //
        //获取前一篇文章和后一篇文章
        final Article previous = articleService.getPreviousArticle(articleId);
        final Article next = articleService.getNextArticle(articleId);
        //获取这篇文章的所有评论
        final List<Comment> comments = commentService.getCommentsByArticle(article);
        //关注点14：获取点击量最多的几篇文章
        //热门文章
        final List<Article> hotArticles = articleService.getArticleByClick(5);
        //文章标签
        final List<Tag> tags = articleService.getTagsByArticleId(articleId);
        //根据文章的标签获取相似文章
        final int size = tags.size();
        final Map<String, Object> tagNames = Maps.newHashMap();
        for (int j = 1; j <= size; j++) {
            tagNames.put("tag" + j, tags.get(j - 1).getName());
        }
        //先获取到相似的标签
        final List<Tag> tagList = articleService.getSimilarTag(tagNames);
        //根据相似的标签的ID去获取此相似的文章
        final List<Article> articles = Lists.newArrayList();

        //获取推荐的文章的id
        final List<Long> articleIds = articleService.getRecommandArticleId();
        //获取推荐的文章
        final List<Article> recommandArticles = articleService.getRecommandArtice(articleIds);
        for (Tag tag : tagList) {
            if (tag.getArticleId() != articleId) {
                Article article1 = articleService.getArticleById(tag.getArticleId());
                articles.add(article1);
            }
        }
        LOGGER.info("similar article size = {}", articles.size());
        handleTime(article);
        final Map<String, Object> map = Maps.newHashMap();
        map.put("article", article);
        map.put("comments", comments);
        map.put("previous", previous);
        map.put("next", next);
        map.put("hotArticles", hotArticles);
        map.put("tags", tags);
        map.put("articles", articles);
        map.put("recommandArticles", recommandArticles);
        return new ModelAndView(FWD_ARTICLE_DETAIL, map);
    }


    /**
     * 去写文章页面
     */
    @RequestMapping(value = {URL_ARTICLE_POSTEDIT_REQUEST})
    public ModelAndView readArticle() {
        LOGGER.info("enter article edit page");
        final Map<String, Object> map = Maps.newHashMap();
        final List<Category> categories = categoryService.getAllCategory();
        map.put("categories", categories);
        return new ModelAndView(FWD_ARTICLE_POSTEDIT, map);
    }

    /**
     * 写文章，从前台的富文本编辑器上获取数据
     * <p>
     */
    @RequestMapping(value = {URL_ARTICLE_INSERT}, method = {POST})
    public ModelAndView insertArticle(HttpServletRequest request, Article article) {
        LOGGER.info("insert Article,articleTitle={}", article.getTitle());
        //新文章编写日期
        article.setPublishDate(new Date());
        //新文章点击量
        article.setClick(0L);
        //获取当前登录的用户，也就是新文章的作者是谁
        final Account account = getCurrentAccount(request.getSession());//注:这里可能会因为session过期而出现异常
        article.setAuthor(account.getNickname());
        if (article.getContent() == null) {
            LOGGER.info("文章无任何内容");
            return new ModelAndView(FWD_FAIL_PAGE);
        }
        //获取从前台传递过来的Tag，本来想用动态脚本创建的，但是水平太LOW，JS写不出来，只能手动创建4个标签
        int i = articleService.insertArticle(article, request);
        if (i == 0) {
            LOGGER.info("插入文章失败");
            return new ModelAndView(FWD_FAIL_PAGE);
        }
        LOGGER.info("current articleId={}", article.getId());
        return new ModelAndView(FWD_SUCCESS_PAGE);
    }


    /**
     * 去个人中心
     * 携带的参数：用户的文章
     *
     * @return
     */
    @RequestMapping(value = {URL_ARTICLE_MY_BLOG_SPACE})
    public ModelAndView myBlogSpace(HttpSession session) {
        LOGGER.info("enter my blog space page");
        final Map<String, Object> map = Maps.newHashMap();
        final Map<String, Object> params = Maps.newHashMap();
        //获取当前用户
        final Account account = getCurrentAccount(session);
        //当前用户的昵称
        String authorName = account.getNickname();
        //获取此作者的所有文章
        params.put("author", authorName);
        final List<Article> articles = articleService.getAllArticle(params);
        //获取阅读排行的文章,根据点击量click降序DESC获取文章
        params.put("condition", "click");
        params.put("sort", "DESC");
        final List<Article> hotArticles = articleService.getArticleByCondition(params);
        //获取此作者的所有评论
        params.put("name", authorName);
        final List<Comment> comments = commentService.getAllComment(params);
        //文章评论---文章标题
        final List<Model> models = Lists.newArrayList();
        for (Comment comment : comments) {
            Long articleId = comment.getArticleId();
            Article article = articleService.getArticleById(articleId);
            models.add(new Model(comment.getContent(), article.getTitle(), articleId));
        }
        map.put("models", models);
        map.put("articles", articles);
        map.put("comments", comments);
        map.put("hotArticles", hotArticles);
        LOGGER.info("author={}", account.getNickname());
        LOGGER.info("article size={}", articles.size());
        LOGGER.info("comments size={}", comments.size());
        return new ModelAndView(FWD_ARTICLE_BLOG_SPACE, map);
    }

    /**
     * 去文章管理页面
     * 携带的参数有:menus,articles
     * 需要进行分页
     *
     * @return 携带上菜单数据，文章数据，去文章管理页面
     */
    @RequestMapping(value = {URL_ARTICLE_MANAGER})
    public ModelAndView articleManager(HttpServletRequest request) {
        LOGGER.info("enter articleManager page");
        final Map<String, Object> map = Maps.newHashMap();
        final Map<String, Object> params = Maps.newHashMap();
        final List<Menu> menus = menuService.obtainMenus();
        String str_pageNo = request.getParameter("pageNo");
        if (str_pageNo == null) {
            PageHelper.startPage(1, 4);
        } else {
            PageHelper.startPage(Integer.parseInt(str_pageNo), 4);
        }
        final List<Article> articles = articleService.getAllArticle(params);
        final PageInfo<Article> pageInfo = new PageInfo<>(articles);
        final int size = articles.size();
        for (int i = 0; i < size; i++) {
            Article article = articles.get(i);
            //处理时间
            handleTime(article);
        }

        map.put("articles", articles);
        map.put("menus", menus);
        map.put("pageInfo", pageInfo);
        return new ModelAndView(FWD_ARTICLE_MANAGER, map);
    }

    /**
     * 展示搜索结果
     * 然后去一个与当前页面一模一样的展示数据的页面
     *
     * @param conditionValue 参数是什么，作者还是文章ID？
     * @param searchValue    如果是作者，作者的值是多少。如果是ID,那么id的值又是多少
     * @return 携带上处理结果，去search结果页面
     */
    @RequestMapping(value = {URL_ARTICLE_SEARCH}, method = {POST})
    public ModelAndView articleSearch(@RequestParam("conditionValue") String conditionValue
            , @RequestParam("searchValue") String searchValue) {
        LOGGER.info("conditionValue={}", conditionValue);
        LOGGER.info("searchValue", searchValue);
        final Map<String, Object> map = Maps.newHashMap();
        final Map<String, Object> params = Maps.newHashMap();
        params.put(conditionValue, searchValue);
        final List<Article> articles = articleService.getArticleByCondition(params);
        int size = articles.size();
        for (int i = 0; i < size; i++) {
            Article article = articles.get(i);
            //处理时间
            handleTime(article);
        }
        map.put("menus", menuService.obtainMenus());
        map.put("articles", articles);
        return new ModelAndView(FWD_ARTICLE_SEARCH, map);
    }

    /**
     * 删除文章
     *
     * @param request  request对象，用于获取文章的articleId
     * @param response 响应对象，用于重定向
     * @return 如果成功，返回的还是当前页面。
     */
    @RequestMapping(value = {URL_ARTICLE_DELETE})
    public ModelAndView articleDelete(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("delete article");
        Long articleId = Long.parseLong(request.getParameter("articleId"));
        LOGGER.info("articleId={}", articleId);
        //删除这篇文章
        Article article = articleService.getArticleById(articleId);
        int i = articleService.deleteArticle(article);

        if (i == 0) {
            LOGGER.info("删除失败");
            return new ModelAndView(FWD_FAIL_PAGE);
        }
        try {
            LOGGER.info("redirect");
            response.sendRedirect(URL_WEB_ROOT + URL_ARTICLE_MANAGER);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();

    }

    /**
     * 从后台发出的查看文章的请求
     *
     * @return 跳转展示文章的页面
     */
    @RequestMapping(value = {URL_ARTICLE_VIEW})
    public ModelAndView articleView(HttpServletRequest request) {
        LOGGER.info("enter article view page");
        final Long articleId = Long.parseLong(request.getParameter("articleId"));
        LOGGER.info("articleId={}", articleId);
        final Article article = articleService.getArticleById(articleId);
        handleTime(article);
        final Map<String, Object> map = Maps.newHashMap();
        final List<Menu> menus = menuService.obtainMenus();
        map.put("article", article);
        map.put("menus", menus);
        return new ModelAndView(FWD_ARTICLE_VIEW, map);
    }

    /**
     * 处理Article实体类中的时间格式
     * Date类型转为通用的字符串类型
     * 用于在页面上展示最简单的时间
     *
     * @param article 文章实体
     */
    private void handleTime(final Article article) {
        Date date = article.getPublishDate();
        //处理时间
        String str_date = DateFormat.getDateTimeInstance().format(date);
        article.setTime(str_date);
    }
}
