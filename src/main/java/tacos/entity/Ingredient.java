package tacos.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * @author RyanLoong
 * @Date 2021/5/5 19:00
 * @Classname Ingredient
 * @Description 表示成分的实体类
 */

//lombok生成所有缺失的方法(getter,setter等),还会生成final属性作为参数等构造器
//@Data隐式地添加了一个必需的有参构造函数,但是当使用@NoArgsConstructor 时,该构造函数将被删除。
@Data
//因此显式的@RequiredArgsConstructor确保除了私有无参数构造函数外,仍然有一个必需有参构造函数。
@RequiredArgsConstructor
//JPA要求实体需要一个无参构造函数,lombok注解实现这个
//但不希望使用它,将access属性设为private
//因为必须设置final属性,所以还要将force属性设置为true,这将导致Lombok生成的构造函数将它们设置为null
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
//将类声明为JPA实体
//Spring Data JPA根据实体类自动创建表(不关闭自动创建功能,关闭则调用scheme.sql和data.sql)
@Entity
public class Ingredient {
    //id属性必须使用@Id注解,以便将其指定为唯一标识
    @Id
    private final String id;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}

