package com.xxl.dao.impl;

import com.xxl.dao.UserDao;
import com.xxl.pojo.Users;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @类名： com.xxl.dao.impl.UserDaoImpl
 * @描述： <尽量简短的描述类的作用>
 * @创建人： xiao.xl
 * @创建时间： 2019/1/2 23:01
 * @修改人和其他信息：
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext(name="entityManagerFactory")
    private EntityManager entityManager;

    @Override
    @Transactional
    @Rollback(false)
    public void insertUser(Users users) {
        this.entityManager.persist(users);
    }

    @Override
    public void updateUser(Users users) {
        this.entityManager.merge(users);
    }

    @Override
    public void deleteUser(Users users) {
        Users u = this.selectUserById(users.getUserId());
        this.entityManager.remove(u);
    }

    @Override
    public Users selectUserById(Integer userId) {
        return this.entityManager.find(Users.class,userId);
    }

    @Override
    public List<Users> selectAll() {
        List<Users> users = this.entityManager.find(List.class, null);
        return users;
    }

    @Override
    public List<Users> selectByUserName(String userName) {
        //从1开始   而hibernate是要从0开始的
        Query name = this.entityManager.createQuery("from Users where userName = :name").setParameter("name", userName);
        return name.getResultList();
    }

    @Override
    public List<Users> selectByUserNameSQL(String userName) {
        Query query = this.entityManager.createNativeQuery("select * from my_user where  user_name = ?", Users.class).setParameter(1, userName);
        return query.getResultList();
    }

    @Override
    public List<Users> selectByUserNameCriteria(String userName) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Users> query = criteriaBuilder.createQuery(Users.class);
        Root<Users> root = query.from(Users.class);
        Predicate userName1 = criteriaBuilder.equal(root.get("userName"), userName);
        query.where(userName1);
        TypedQuery<Users> query1 = this.entityManager.createQuery(query);
        return query1.getResultList();
    }
}
