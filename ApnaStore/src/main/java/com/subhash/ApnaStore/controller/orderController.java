package com.subhash.ApnaStore.controller;

import com.subhash.ApnaStore.model.customer;
import com.subhash.ApnaStore.model.order;
import com.subhash.ApnaStore.model.product;
import com.subhash.ApnaStore.repository.customerRepository;
import com.subhash.ApnaStore.repository.orderRepository;
import com.subhash.ApnaStore.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class orderController {

    @Autowired
    private orderRepository orderRepository;

    @Autowired
    private customerRepository customerRepository;

    @Autowired
    private productRepository productRepository;

    @GetMapping("/customer/order/{phone}")
    public String customerOrder(@PathVariable("phone") long phone, Model model){
        try{
            List<order> orders = orderRepository.findAll(phone);
            System.out.println(orders.size());
            customer customer = customerRepository.findById(phone);
            model.addAttribute("customer",customer);
            model.addAttribute("orders",orders);
            return "order/order";
        }
        catch(Exception e){
            System.out.println(e.toString());
            return "error";
        }
    }

    @GetMapping("/order/add/{phone}")
    public String addOrder(@PathVariable("phone") long phone, Model model){
        try{
            order Order = new order();
            //customerOrder Order = new customerOrder();
            System.out.println(phone);
            Order.setCustomerId(phone);
            model.addAttribute("order",Order);
            return "order/form";
        }
        catch(Exception e){
            return "error";
        }
    }

    @PostMapping("/order/add")
    public String postOrder(@ModelAttribute order Order,Model model){
        try{
            //fetch customer
            customer customer = customerRepository.findById(Order.getCustomerId());
            System.out.println(Order.toString());
            //fetch product
            product Product = productRepository.findById(Order.getProductId());
            //update total amount of customer
            long total = customer.getTotal() + Order.getQty() * Product.getPrice();
            customer.setTotal(total);
            //update customer
            int res = customerRepository.update(customer);
            System.out.println(res);
            //add order
            res = orderRepository.addOrder(Order);

            System.out.println(res);
            model.addAttribute("customer",customer);
            List<order> orders = orderRepository.findAll(Order.getCustomerId());
            model.addAttribute("orders",orders);
            return "order/order";
            //return "home";
        }
        catch(Exception e){
            return "error";
        }
    }
}
