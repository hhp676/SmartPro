package domains.menu.repository.mybatis;

import domains.menu.entity.Menu;
import domains.menu.repository.MenuDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hhp on 2018/2/6.
 */
@Repository("MenuDao")
public final class MenuDaoImpl extends SqlSessionDaoSupport implements MenuDao {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public List<Menu> obtainMenus() {
        final String statement = "obtainMenus";
        return this.getSqlSession().selectList(statement);
    }
}
