package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        Iterable<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createShow() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCreate(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {

        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/customer/error");
            return modelAndView;
        }
    }

    @PostMapping("edit")
    public String editSave(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:customer";
    }
@GetMapping("delete/{id}")
    public ModelAndView deleteShow(@PathVariable Long id) {
    Optional<Customer> customer = customerService.findById(id);
    if (customer.isPresent()) {
        ModelAndView modelAndView = new ModelAndView("/customer/delete");
        modelAndView.addObject("customer", customer.get());
        return modelAndView;

    }else {
        ModelAndView modelAndView=new ModelAndView("/customer/error");
        return modelAndView;
    }
}
@PostMapping("delete")
    public String deleteSave(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:customer";
}
@PostMapping("searchName")
    public ModelAndView searchName(@RequestParam String province){
       Iterable<Customer> customer= customerService.findByProvince(province);
       ModelAndView modelAndView=new ModelAndView("/customer/searchName");
       modelAndView.addObject("customers",customer);
       return modelAndView;
}

}
