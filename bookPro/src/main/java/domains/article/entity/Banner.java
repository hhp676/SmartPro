package domains.article.entity;

/**
 * Created by hhp on 2018/3/23.
 * 文章标签，对应数据库t_tag表
 */
public final class Banner {
    private int id;//标签主键

    private String name;//标签名称

    private String urlval;

    private String title;

    private String text;

    private String isdel;

    private String flag;

    private String imgurl;


  /*  public Banner() {
    }

    public Banner(String name, Long articleId) {
        this.name = name;
        this.articleId = articleId;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlval() {
        return urlval;
    }

    public void setUrlval(String urlval) {
        this.urlval = urlval;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    /*@Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("articleId", articleId)
                .toString();
    }*/
}
