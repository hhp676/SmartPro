package domains.menu.repository;


import domains.menu.entity.Menu;

import java.util.List;

/**
 * Created by hhp on 2018/2/6.
 */
public interface MenuDao {
    /**
     * 获取所有可用的菜单
     *
     * @return 所有可用菜单
     */
    List<Menu> obtainMenus();
}
