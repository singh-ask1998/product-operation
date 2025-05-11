package com.ezpz.ecom.ezstore.product.operation.service;

import com.ezpz.ecom.ezstore.product.common.util.APIResponse;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDTO;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDetailMultilingualDTO;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDetailsDTO;

public interface ProductDashboardService {

	ProductDTO getProductById(Long id);
	
	APIResponse addProduct(ProductDTO productDTO);
	
	APIResponse addProductDetail(ProductDetailsDTO productDetailsDTO, Long productId);
	
	APIResponse addProductDetailMultilingual(ProductDetailMultilingualDTO productDetailMultilingualDTO,Long serialNumber);
	
	
}
