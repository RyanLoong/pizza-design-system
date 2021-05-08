package tacos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author RyanLoong
 * @Date 2021/5/5 22:17
 * @Classname WebConfig
 * @Description 控制器的配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 直接在配置类中注册一个控制器，将"/"路径上的请求返回"home"视图
        registry.addViewController("/").setViewName("home");
        // 由于十分简单我们直接在这个里面添加一个就可以了
        registry.addViewController("/login");
    }
}
