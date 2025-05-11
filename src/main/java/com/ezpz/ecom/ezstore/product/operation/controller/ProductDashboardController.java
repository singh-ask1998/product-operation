package com.ezpz.ecom.ezstore.product.operation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezpz.ecom.ezstore.product.common.model.Price;
import com.ezpz.ecom.ezstore.product.common.util.APIResponse;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDTO;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDetailMultilingualDTO;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDetailsDTO;
import com.ezpz.ecom.ezstore.product.operation.service.ProductDashboardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product Management", description = "Operations related to Products")
@RestController
@RequestMapping("/product")
public class ProductDashboardController {
	
	@Autowired
	private ProductDashboardService productDashboardService;
	
	@Operation(summary = "Get product detail", description = "Retrieve product details")
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<?> getProductDetails(@PathVariable Long id) {
		
		productDashboardService.getProductById(id);
		return  ResponseEntity.status(200)
				              .body("Successfully reached"+id);
		
	}
	@Operation(summary = "Add product", description = "Add product")
	@PostMapping(path="/addProduct",consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
		APIResponse apiResponse= productDashboardService.addProduct(productDTO);
		 return ResponseEntity.status(200).body(apiResponse);
	}
	@Operation(summary = "Add product detail", description = "Add product details")
	@PostMapping("/addProductDetails/{productId}")
	public ResponseEntity<?> addProductDetail(@PathVariable Long productId ,@RequestBody() ProductDetailsDTO productDetailsDTO){
		APIResponse apiResponse= productDashboardService.addProductDetail(productDetailsDTO,productId);
		 return ResponseEntity.status(200).body(apiResponse);
	}
	@Operation(summary = "Add product detail in different languages", description = "Add product detail in different languages")
	@PostMapping("/addProductDetailsMultiLingual/{serailNumber}")
	public ResponseEntity<?> addProductDetailsMultiLingual(@PathVariable Long serailNumber ,@RequestBody ProductDetailMultilingualDTO productDetailMultilingualDTO){
		APIResponse apiResponse= productDashboardService.addProductDetailMultilingual(productDetailMultilingualDTO,serailNumber);
		 return ResponseEntity.status(200).body(apiResponse);
	}
	@Operation(summary = "Add product detail in different languages", description = "Add product detail in different languages")
	@PostMapping("/dummy/{productId}")
	public ResponseEntity<?> dummy(@PathVariable Long productId ,@RequestBody Price productDetailMultilingualDTO){
		return null;
	}
	@GetMapping("/getDemo")
	public String fetchDemo() {
		return "Demo";
	}

}
