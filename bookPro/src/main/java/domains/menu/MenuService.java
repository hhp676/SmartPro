package domains.menu;


import domains.menu.entity.Menu;

import java.util.List;

/**
 * Created by hhp on 2018/2/6.
 */
public interface MenuService {
    /**
     * 获取所有的菜单
     *
     * @return 从数据库查询到的所有菜单
     */
    List<Menu> obtainMenus();
}
