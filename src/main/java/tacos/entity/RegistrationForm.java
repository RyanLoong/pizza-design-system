package tacos.entity;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author RyanLoong
 * @Date 2021/5/7 21:27
 * @Classname RegistrationForm
 * @Description 注册时候表单提交时，对表单的封装，
 * 在封装之后通过toUser方法可以将我们封装好的注册表单对象转换成user对象
 */
@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password),
                fullname,street,city,state,zip,phone);
    }
}
