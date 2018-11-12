package pl.paciorek.dawid.finalinvoicesdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.paciorek.dawid.finalinvoicesdb.model.Product;
import pl.paciorek.dawid.finalinvoicesdb.repository.ProductRepository;
import pl.paciorek.dawid.finalinvoicesdb.repository.UserRepository;

@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @GetMapping("/admin/users")
    public String postsPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @PostMapping("/admin/add")
    public String addProduct(@RequestParam(value = "name") String name,
                             @RequestParam(value = "price") int price,
                             @RequestParam(value = "quantity") int quantity, Model model) {
        model.addAttribute("success", "The product has been successfully entered.");
        Product product = new Product(name, price, quantity);
        productRepository.save(product);
        return "admin";
    }
}
