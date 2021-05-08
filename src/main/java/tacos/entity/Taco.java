package tacos.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author RyanLoong
 * @Date 2021/5/5 19:24
 * @Classname Taco
 * @Description Taco披萨的实体类
 */
@Data
@Entity
public class Taco {
    /**
     * 因为依赖于数据库自动生成id值,所以还使用@GeneratedValue注解id属性,指定自动策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt;
    @NotBlank
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    /**
     * 多对多关系
     * @ManyToMany 注解ingredient属性,一个Taco可以有很多Ingredient,一个Ingredient可以是很多Taco的一部分
     */
    @ManyToMany(targetEntity = Ingredient.class)
    @NotEmpty
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
    /**
     * 声明在实体持久化保存之前触发的回调方法
     */
    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
