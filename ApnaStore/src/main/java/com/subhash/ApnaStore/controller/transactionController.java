package com.subhash.ApnaStore.controller;

import com.subhash.ApnaStore.model.customer;
import com.subhash.ApnaStore.model.order;
import com.subhash.ApnaStore.model.product;
import com.subhash.ApnaStore.model.transaction;
import com.subhash.ApnaStore.repository.customerRepository;
import com.subhash.ApnaStore.repository.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class transactionController {

    @Autowired
    private transactionRepository transactionRepository;

    @Autowired
    private customerRepository customerRepository;

    @GetMapping("/customer/transaction/{phone}")
    public String customerOrder(@PathVariable("phone") long phone, Model model){
        try{
            List<transaction> transactions = transactionRepository.findAll(phone);
            System.out.println(transactions.size());
            customer customer = customerRepository.findById(phone);
            model.addAttribute("customer",customer);
            model.addAttribute("transactions",transactions);
            return "transaction/transaction";
        }
        catch(Exception e){
            System.out.println(e.toString());
            return "error";
        }
    }

    @GetMapping("/transaction/add/{phone}")
    public String addOrder(@PathVariable("phone") long phone, Model model){
        try{
            transaction transaction = new transaction();
            System.out.println(phone);
            transaction.setCustomerId(phone);
            model.addAttribute("transaction",transaction);
            return "transaction/form";
        }
        catch(Exception e){
            return "error";
        }
    }

    @PostMapping("/transaction/add")
    public String postOrder(@ModelAttribute transaction transaction, Model model){
        try{
            //fetch customer
            customer customer = customerRepository.findById(transaction.getCustomerId());
            System.out.println(transaction.toString());
            //update total amount of customer
            long total = customer.getTotal() - transaction.getAmount();
            customer.setTotal(total);
            //update customer
            int res = customerRepository.update(customer);
            System.out.println(res);
            //add order
            res = transactionRepository.addOrder(transaction);

            System.out.println(res);
            model.addAttribute("customer",customer);
            List<transaction> transactions = transactionRepository.findAll(transaction.getCustomerId());
            model.addAttribute("transactions",transactions);
            return "transaction/transaction";
            //return "home";
        }
        catch(Exception e){
            return "error";
        }
    }
}
