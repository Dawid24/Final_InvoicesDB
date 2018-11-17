package pl.paciorek.dawid.finalinvoicesdb.controller;

import jdk.management.resource.ResourceRequestDeniedException;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.paciorek.dawid.finalinvoicesdb.model.Invoice;
import pl.paciorek.dawid.finalinvoicesdb.model.Product;
import pl.paciorek.dawid.finalinvoicesdb.model.Utils.OrderUtils;
import pl.paciorek.dawid.finalinvoicesdb.repository.InvoiceRepository;
import pl.paciorek.dawid.finalinvoicesdb.repository.ProductRepository;
import pl.paciorek.dawid.finalinvoicesdb.repository.UserRepository;

import javax.validation.Valid;


@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

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

    @GetMapping("/admin/invoices")
    public String invoicesPage(Model model) {
        model.addAttribute("invoices", invoiceRepository.findAll());
        return "invoices";
    }

    @GetMapping("/admin/invoices/{id}")
    public String getNoteById(@PathVariable(value = "id") Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()-> new ResourceClosedException("id"));
        invoice.setStatus(OrderUtils.COMPLETE.toString());
        System.out.println(invoice.getStatus());
        invoiceRepository.save(invoice);
        return "invoices";
    }

    /*@PostMapping("/admin/invoices/{id}")
    public String updateNoteById(@PathVariable(value = "id")Long invoiceId, @Valid @RequestBody Invoice invoiceDetails) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()-> new ResourceClosedException("id"));
        invoice.setStatus(OrderUtils.COMPLETE.toString());
        System.out.println(invoice.getStatus());
        invoiceRepository.save(invoice);
        return "invoices";
    }*/

    @PostMapping("/admin/add")
    public String addProduct(@RequestParam(value = "name") String name,
                             @RequestParam(value = "price") int price, Model model) {
        model.addAttribute("success", "The product has been successfully entered.");
        Product product = new Product(name, price);
        productRepository.save(product);
        return "admin";
    }
}
