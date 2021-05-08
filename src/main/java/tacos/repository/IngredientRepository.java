package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.entity.Ingredient;

/**
 * @author RyanLoong
 * @Date 2021/5/6 23:04
 * @Classname IngredientRepository
 * @Description ingredient的jpa持久化层
 * 在CrudRepository这个类的泛型中第一个参数表示待持久化的对象，第二参数表示持久化的对象的主键
 * 这个类将主要的crud操作都进行了封装
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
