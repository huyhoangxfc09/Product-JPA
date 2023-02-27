package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductRepository iProductRepository;
    @Override
    public List<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return iProductRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        product.setImagePath(getFileName(product));
        iProductRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }
    @Value("${file-upload}")
    private String fileUpload;
    public String getFileName(Product product) {
        MultipartFile image = product.getImage();
        String fileName = image.getOriginalFilename();
        try {
            FileCopyUtils.copy(image.getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileName;
    }
    @Override
    public List<Product> searchByName(String name){
            return iProductRepository.findByName("%"+name+"%");
    }
}
