package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.entity.User;

/**
 * @author RyanLoong
 * @Date 2021/5/7 20:29
 * @Classname UserRepository
 * @Description 用于查询User的持久层接口
 */
public interface UserRepository extends CrudRepository<User,Long> {
    /**
     * 通过username查询到user对象
     * @param username 用户名称
     * @return 返会一个user对象
     */
    User findByUsername(String username);
}
