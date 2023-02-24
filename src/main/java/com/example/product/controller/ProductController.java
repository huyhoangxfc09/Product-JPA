package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @GetMapping
    public String listProduct(Model model){
        List<Product> products = iProductService.findAll();
        model.addAttribute("product",products);
        return "list";
    }
    @GetMapping("create")
    private ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("create","create");
        return modelAndView;
    }
    @PostMapping("/create")
    private String createClassroom(@ModelAttribute Product product, RedirectAttributes attributes){
        iProductService.save(product);
        attributes.addFlashAttribute("message","Tạo mới thành công");
        return "redirect:/products";
    }
    @GetMapping("/update/{id}")
    private ModelAndView updateForm(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("update","update");
        modelAndView.addObject("product",iProductService.findById(id));
        return modelAndView;
    }
    @PostMapping("/update/{id}")
    private String updateProduct(@ModelAttribute Product product, RedirectAttributes attributes){
        iProductService.save(product);
        attributes.addFlashAttribute("message","Update thành công");
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    private String deleteClassroom(@PathVariable("id")Long id){
        iProductService.remove(id);
        return "redirect:/products";
    }
    @GetMapping("/detail/{id}")
    private ModelAndView detailProduct(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = iProductService.findById(id);
        modelAndView.addObject("product",product);
        return modelAndView;
    }
    @PostMapping("/search")
    private ModelAndView searchByName(@RequestParam("search")String name){
        List<Product> products = iProductService.searchByName(name);
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("product",products);
        return modelAndView;
    }
}
