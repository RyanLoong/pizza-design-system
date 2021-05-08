package tacos.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.entity.RegistrationForm;
import tacos.repository.UserRepository;

/**
 * @author RyanLoong
 * @Date 2021/5/7 21:18
 * @Classname RegistrationController
 * @Description 用户注册控制器
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    /**
     * 密码转换器
     */
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        // 将注册表单中的数据对象转换封装程user对象然后将其持久化
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
