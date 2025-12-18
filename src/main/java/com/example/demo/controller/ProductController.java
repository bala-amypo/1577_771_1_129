// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import jakarta.validation.Valid;
// import com.example.demo.model.Product;
// import com.example.demo.service.ProductService;

// import org.springframework.web.bind.annotation.RequestBody;

// @RestController
// @RequestMapping("/api/products")
// public class ProductController {
//     @Autowired
//     ProductService productService;
//     @PostMapping
//     public Product create(@Valid @RequestBody Product product){ 
//         System.out.println("in Controller "+product.getName());
//         return productService.createProduct(product);
 
//     }
//     // @PutMapping("/{id}")
//     // public String putbyid(Long id,@RequestBody Product product){
//     //     if(productService.updateProduct(id,product) != null){
//     //         return "SuccessFul";
//     //     }
//     //     return "Not sucessfull";
//     // }
//     @GetMapping("/{id}")
//     public Product getbyid(@PathVariable Long id){
//         return productService.getProductById(id);
//     }
//     @GetMapping
//     public List<Product> getll(@PathVariable Long id){
//         return productService.getAllProducts();
//     }
//     // @PutMapping("/{id}/deactivate")
//     // public ResponseEntity<String> put(@PathVariable Long id){
//     //      productService.deactiveProduct(id);
//     //      return ResponseEntity.ok("Product deactivated");
//     // }


// }
