package com.subhash.ApnaStore.controller;

import com.subhash.ApnaStore.model.product;
import com.subhash.ApnaStore.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class productController {

    @Autowired
    private productRepository productRepository;

    @GetMapping("/product/add")
    public String addProduct(Model model){
        try{
            product product = new product();
            model.addAttribute("product",product);
            return "product/form";
        }
        catch (Exception e){
            return "error";
        }
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute product Product){
        try{
            int res = productRepository.add(Product);
            if(res > 0){
                return "home";
            }
            else{
                return "product/unique";
            }

        }
        catch(Exception e){
            return "error";
        }
    }
}
