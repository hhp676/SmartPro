package domains.user.repository.mybatis;


import domains.user.entity.User;
import domains.user.repository.UserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by hhp on 2017/12/12.
 */
@Repository("UserDao")
public final class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insertLoginInfo(Map<String, Object> map) {
        LOGGER.info("Repository:insertLoginInfo,map={}", map);
        return this.getSqlSession().insert("insertLoginInfo", map);
    }

    @Override
    public String findSaltByLoginName(String loginName) {
        LOGGER.info("Repository:findSaltByLoginName,loginName={}", loginName);
        return this.getSqlSession().selectOne("findSaltByLoginName", loginName);
    }

    @Override
    public int updatePassword(Map<String, String> map) {
        LOGGER.info("Repository:updatePassword,map={}", map);
        return this.getSqlSession().update("updatePassword", map);
    }

    @Override
    public int insertRoleForNewAccount(Long id) {
        LOGGER.info("Repository:updatePassword,Account Id={}", id);
        return this.getSqlSession().insert("insertRoleForNewAccount", id);
    }
}
