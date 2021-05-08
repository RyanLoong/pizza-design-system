package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.entity.Taco;

/**
 * @author RyanLoong
 * @Date 2021/5/6 23:06
 * @Classname TacoRepository
 * @Description taco对象的JPA持久层
 */
public interface TacoRepository extends CrudRepository<Taco, Long> {
}
