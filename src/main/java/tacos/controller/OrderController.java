package tacos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;;
import org.springframework.web.bind.support.SessionStatus;
import tacos.config.OrderProps;
import tacos.entity.Order;
import tacos.entity.User;
import tacos.repository.OrderRepository;

import javax.validation.Valid;
import java.security.Principal;

/**
 * @author RyanLoong
 * @Date 2021/5/5 21:26
 * @Classname OrderController
 * @Description 订单控制器，用于处理taco订单表单的控制器
 */
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    private OrderRepository orderRepo;
    private OrderProps props;

    @Autowired
    public OrderController(OrderRepository orderRepo, OrderProps props) {
        this.orderRepo = orderRepo;
        this.props = props;
    }

    /**
     * 在设计被提交之后需要，保存购买者信息，通过get方法将order传递过去，
     * 通过表单对数据对象进行填充
     */
    @GetMapping("/current")
    public String orderForm(Model model) {
        return "/orderForm";
    }

    /**
     * 处理页面提交过来的包含order对象的表单信息。
     * @AuthenticationPrincipal 可以获取到user上下文对象，其实就是相当于我们使用threadLocal存了一下user
     */
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        orderRepo.save(order);
        // 重置session，也就是将session中的order都清除
        sessionStatus.setComplete();
        logger.info("Order submitted: " + order);

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0,props.getPageSize());
        model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        // 渲染订单页面
        return "orderList";
    }
}
