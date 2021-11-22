package com.subhash.ApnaStore.controller;

import com.subhash.ApnaStore.model.customer;
import com.subhash.ApnaStore.repository.customerRepository;
import com.subhash.ApnaStore.repository.orderRepository;
import com.subhash.ApnaStore.repository.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class customerController {

    @Autowired
    private customerRepository customerRepository;

    @Autowired
    private orderRepository orderRepository;

    @Autowired
    private transactionRepository transactionRepository;

    @GetMapping("/customer")
    public String allCustomer(Model model){
        //return customerRepository.findAll();
        List<customer> customers = customerRepository.findAll();
        customer c = new customer();
        model.addAttribute("customers",customers);
        model.addAttribute("customer",c);
        return "customer/customer.html";
    }

    @GetMapping("/customer/delete/{phone}")
    public String deleteCustomer(@PathVariable("phone") long phone,Model model){
        int res = customerRepository.deleteById(phone);
        System.out.println(res);
        res = orderRepository.deleteById(phone);
        System.out.println(res);
        res = transactionRepository.deleteById(phone);
        System.out.println(res);
        List<customer> customers = customerRepository.findAll();
        customer c = new customer();
        model.addAttribute("customers",customers);
        model.addAttribute("customer",c);
        return "customer/customer.html";

    }

    @GetMapping("/customer/update/{phone}")
    public String updateCustomer(@PathVariable("phone") long phone, Model model){
        customer customer = customerRepository.findById(phone);
        model.addAttribute("customer", customer);
        return "customer/update";
    }

    @PostMapping("/customer/update/{phone}")
    public String updateCustomer(@PathVariable("phone") long phone, @ModelAttribute customer customer,Model model){
        int res = customerRepository.deleteById(phone);
        System.out.println(res);
        System.out.println(customer);
        res = customerRepository.add(customer);
        System.out.println(res);
        model.addAttribute("customer",customer);
        return "customer/profile";
    }

    @GetMapping("/customer/add")
    public String addCustomer(Model model){
        model.addAttribute("customer",new customer());
        return "customer/form.html";
    }


    @PostMapping("/customer/add")
    public String addCustomer(@ModelAttribute customer customer){
        try{
            //model.addAttribute("customer",customer);
            System.out.println(customer);
            customerRepository.add(customer);
            return "home"; //return succes home page
           // return new ResponseEntity<>("successfully added!", HttpStatus.CREATED);
        }
        catch(Exception e){
            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            return "customer/form";
        }
    }

    @GetMapping("/customer/{phone}")
    public String customerProfile(@PathVariable("phone") long phone, Model model){
        try{
            customer c = customerRepository.findById(phone);
            model.addAttribute("customer",c);
            return "customer/profile";
        }
        catch (Exception e){
            return "error";
        }
    }


    @PostMapping("/customer/search")
    public String searchCustomer(@ModelAttribute customer c, Model model){
        try{
            long phone = c.getPhone();
            System.out.println(c.toString());
            customer customer = customerRepository.findById(phone);
            System.out.println(customer.toString());
            if(customer == null){
                return "home";
            }
            else{
                model.addAttribute("customer",customer);
                return "customer/profile";
            }
        }
        catch(Exception e){
            //System.out.println(e.toString());
            //return "error";
            return "customer/result";
        }

    }



}
