package tacos.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.entity.Order;
import tacos.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @author RyanLoong
 * @Date 2021/5/6 23:08
 * @Classname OrderRepository
 * @Description 对于订购者order对象的持久化操作层
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
    /**
     * 通过右边查询使用指定邮编的订购者
     * @param deliveryZip 邮编
     * @return 返回一个订购集合
     */
    List<Order> findByDeliveryZip(String deliveryZip);

    /**
     * 通过右边和创建时间来读取所有的订购者，方法中的Between表示的是时间的范围查询
     * @param deliveryZip 邮编
     * @param startData 开始时间
     * @param endDate 结束时间
     * @return 订单集合
     */
    List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startData, Date endDate);

    /**
     * 通过user对象去查询订单并降序显示
     * @param user user对象
     * @return 返回一个订单集合
     */
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
