package pl.paciorek.dawid.finalinvoicesdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.paciorek.dawid.finalinvoicesdb.repository.UserRepository;

@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

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
}
