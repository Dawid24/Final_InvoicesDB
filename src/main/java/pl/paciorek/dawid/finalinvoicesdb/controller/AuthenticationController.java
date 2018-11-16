package pl.paciorek.dawid.finalinvoicesdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import pl.paciorek.dawid.finalinvoicesdb.model.Invoice;
import pl.paciorek.dawid.finalinvoicesdb.model.Invoice;
import pl.paciorek.dawid.finalinvoicesdb.model.Product;
import pl.paciorek.dawid.finalinvoicesdb.model.User;
//import pl.paciorek.dawid.finalinvoicesdb.model.Utils.OrderUtils;
import pl.paciorek.dawid.finalinvoicesdb.model.Utils.OrderUtils;
import pl.paciorek.dawid.finalinvoicesdb.repository.InvoiceRepository;
import pl.paciorek.dawid.finalinvoicesdb.repository.ProductRepository;
import pl.paciorek.dawid.finalinvoicesdb.repository.UserRepository;
import pl.paciorek.dawid.finalinvoicesdb.service.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        //Invoice invoice = new Invoice();
        //Order order = new Order();
        Product product = new Product();
        String name = principal.getName();
        User us = userService.getActiveUser(name);
        //modelAndView.addObject("invoice", invoice);
        //modelAndView.addObject("order", order);
        modelAndView.addObject("auth_user", us);
        modelAndView.addObject("prod", product);
        modelAndView.addObject("products", productRepository.findAll());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView addProduct(@Valid Product product, BindingResult bindingResult, Principal principal, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);

        }
        String name = principal.getName();
        User us = userService.getActiveUser(name);

        us.getProductList().add(product);
        modelAndView.addObject("user", us);
        modelAndView.addObject("auth_user", us);
        modelAndView.addObject("success", "The product has been successfully bought.");
        modelAndView.addObject("products", productRepository.findAll());
        modelAndView.setViewName("home");
        int sum = 0;
        for (Product p: us.getProductList()) {
            System.out.println(p);
            sum += p.getPrice();
            System.out.println(sum);
        }

        Invoice invoice = new Invoice(sum, OrderUtils.PROGRESS.toString(), us);
        System.out.println(invoice);
        invoiceRepository.save(invoice);
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);

        } else if (userService.isUserAlreadyPresent(user)){
            modelAndView.addObject("successMessage", "User already exists!");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "You have been registered successfully!");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }

}