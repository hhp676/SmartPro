package domains.menu.impl;

import domains.menu.MenuService;
import domains.menu.entity.Menu;
import domains.menu.repository.MenuDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hhp on 2018/2/6.
 */
@Service("MenuService")
public final class MenuServiceImpl implements MenuService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    private MenuDao menuDao;

    /**
     * 获取所有可用的菜单
     *
     * @return 所有可用菜单
     */
    @Override
    public List<Menu> obtainMenus() {
        LOGGER.info("Service:obtainMenus,获取所有菜单");
        return menuDao.obtainMenus();
    }
}
