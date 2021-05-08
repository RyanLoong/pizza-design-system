package tacos.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author RyanLoong
 * @Date 2021/5/5 21:43
 * @Classname Order
 * @Description 订餐者信息
 * 指定实体应该持久化的表名字(Taco_Order)
 * 可以在任何实体类上使用这个注解
 * 如果不使用,则JPA将默认将实体持久化到一个名为Order的表中
 */
@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;
    @NotBlank(message = "Name is required")
    private String deliveryName;
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @NotBlank(message = "City is required")
    private String deliveryCity;
    @NotBlank(message = "State is required")
    private String deliveryState;
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;
    /**
     * 合法的信用卡号,能通过Luhn算法检查
     */
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();
    /**
     * 用于表示用户和order和用户之间的关系，一个订单只能属于一个用户，但是一个用户可以拥有多个订单
     */
    @ManyToOne
    private User user;

    /**
     * 将taco对象添加到订购者的集合中
     * @param design 订购者的taco设计
     */
    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void placeAt() {
        this.placedAt = new Date();
    }
}