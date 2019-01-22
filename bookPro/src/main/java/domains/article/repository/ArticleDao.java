package domains.article.repository;


import domains.article.entity.Article;
import domains.article.entity.Banner;
import domains.article.entity.Tag;

import java.util.List;
import java.util.Map;

/**
 * Created by hhp on 2018/3/7.
 */
public interface ArticleDao {

    /**
     * ����һƪ�µ�����
     *
     * @param article ���¶���
     * @return        �Ƿ�����ɹ���1��ʾ�ɹ���0��ʾʧ��
     */
    int insertArticle(Article article);

    /**
     * ɾ��һƪ����
     *
     * @param article ���¶��󣬲��������¶���֮ǰ��Ҫ��ѯ�������¶���
     * @return        �Ƿ�����ɹ���1��ʾ�ɹ���0��ʾʧ��
     */
    int deleteArticle(Article article);

    /**
     * ��������,ֻ�����������
     *
     * @param article ����ʵ�������Ҫ�Ȳ�ѯ���˶���
     * @return   �Ƿ�����ɹ���1��ʾ�ɹ���0��ʾʧ��
     */
    int updateArticle(Article article);

    /**
     * ͨ��id��ȡ�������£�����������CRUD����
     *
     * @param id ��������id
     * @return   ��ѯ����Articleʵ��������û�У����ؿղζ���
     */
    Article getArticleById(Long id);


    /**
     * ��ȡ��������
     *
     * @param map ���mapû�в����������������¸�����Ա������в��������ܷ��ص���ָ�����ߵ�����
     * @return    �������µ�List����
     */
    List<Article> getAllArticle(Map<String,Object> map);

    /**
     * ��ȡָ�����������£����յ������������
     *
     * @param limit ָ������
     * @return      �����·ŵ�List������
     */
    List<Article> getArticleByClick(Integer limit);

    List<Article> getArticleByRecom(Integer limit);

    List<Article> getArticleByDate(Integer limit);

    List<Banner> getBannerList(Integer limit);

    /**
     * ��ȡǰһƪ����
     *
     * @param currentArticleId ��ǰ���µ�ID��
     * @return                 ��һƪ���µ�ʵ����
     */
    Article getPreviousArticle(Long currentArticleId);

    /**
     * ��ȡ��һƪ����
     *
     * @param currentArticleId ��ǰ���µ�ID��
     * @return                 ��һƪ���µ�ʵ����
     */
    Article getNextArticle(Long currentArticleId);

    /**
     * @param tags ��װ����ƪ���µ�List����
     * @return
     */
    int insertArticleTag(List<Tag> tags);

    /**
     * �������µ�id��ȡ���µ����б�ǩ
     *
     * @param articleId ���µ�id
     * @return          ��ƪ���µ����б�ǩ
     */
    List<Tag> getTagsByArticleId(Long articleId);

    /**
     * ���ݱ�ǩ����map���ϣ���ȡ���Ƶı�ǩ����
     * Ȼ�������Щ��ǩ�����ArticleId
     * ��ȡ����Щ���¶���
     * ��װ��List�����У�ʹ��map���󴫵�ǰ��ҳ��
     *
     * @param tagNames ��װ�Ŷ����ǩ����map����
     * @return         ��map�����еı�ǩ�����Ƶı�ǩ����
     */
    List<Tag> getSimilarTag(Map<String, Object> tagNames);

    /**
     * ������صĹؼ��ֻ�ȡ���Ƽ�������
     * ��ʱ������Ϊ���������ļ�ƪ���£���ôҲ�������ȵ��Ǽ�ƪ����
     *
     * @return �Ƽ�������
     */
    List<Long> getRecommandArticleId();

    /**
     * �������Ƽ������µ�ID����ȡ��Щ����
     *
     * @param articleIds ָ�����µ�id�ļ���
     * @return           ��Щid��Ӧ������
     */
    List<Article> getRecommandArtice(List<Long> articleIds);

    /**
     * ����ָ���������ȡ����
     * SQL��������<if test>������չ</>
     *
     * @param map   ��װ�������map���ϣ�ӵ�������������
     * @return      �����·ŵ�List������
     */
    List<Article> getArticleByCondition(Map<String, Object> map);

    /**
     * ��������idɾ����ǩ���û�ɾ�����µĹ�������
     *
     * @param articleId
     * @return
     */
    int deleteTagByArticleId(Long articleId);
}


















