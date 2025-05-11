package com.ezpz.ecom.ezstore.product.operation.util;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ezpz.ecom.ezstore.product.common.entities.Product;
import com.ezpz.ecom.ezstore.product.common.entities.ProductDetailMultilingual;
import com.ezpz.ecom.ezstore.product.common.entities.ProductDetailMultilingualEmbeddedId;
import com.ezpz.ecom.ezstore.product.common.entities.ProductDetails;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDTO;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDetailMultilingualDTO;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDetailsDTO;

import jakarta.persistence.EntityManager;

@Component
public class OperationUtil {
	
	@Autowired
	EntityManager entityManager;

	public static Boolean ValidateProduct(ProductDTO productDTO) {
		if (productDTO.getUuid() != null && productDTO.getProductIdentifier() != null)
			return true;

		return false;
	}

	public static Product convertProductDTOtoEntity(ProductDTO productDTO) {
		List<ProductDetails> productDetailsList = productDTO.getProductDetailsDTO().stream()
				.map(data -> convertProductDetailsDTOtoEntity(data,null)).collect(Collectors.toList());

		Product product = Product.builder().categoryId(productDTO.getCategoryId()).uuid(productDTO.getUuid())
				.productIdentifier(productDTO.getProductIdentifier()).productDetails(productDetailsList)
				.status(productDTO.getStatus()).tags(productDTO.getTags()).build();
        
		return product;
	}

	public static ProductDetails convertProductDetailsDTOtoEntity(ProductDetailsDTO productDetailsDTO, Long productId) {
		List<ProductDetailMultilingual> productDetailMultilingual = productDetailsDTO.getProductDetailMultilingualDTO()
				.stream().map(data -> (convertProductDetailMultilingualDTOtoEntity(data,null))).collect(Collectors.toList());

		ProductDetails productDetails = ProductDetails.builder().productDetailMultilingual(productDetailMultilingual)
				.priceDetails(productDetailsDTO.getPrice())
				.variationAttribute(productDetailsDTO.getVariationAttribute()).offerId(productDetailsDTO.getOfferId())
				.build();
		if (productId != null) {
			productDetails.setProductId(productId);
		}
		productDetails.getProductDetailMultilingual().forEach(data-> data.setProductDetails(productDetails));
		
		return productDetails;
//    	 ProductDetails productDetails=ProductDetails.builder().	

	}

	public static ProductDetailMultilingual convertProductDetailMultilingualDTOtoEntity(
			ProductDetailMultilingualDTO productDetailMultilingualDTO, Long serialNumber) {
		ProductDetailMultilingualEmbeddedId productDetailMultilingualEmbeddedId = ProductDetailMultilingualEmbeddedId
				.builder().languageCode(productDetailMultilingualDTO.getLanguageCode()).build();
		if(serialNumber!=null) {
			productDetailMultilingualEmbeddedId.setSerialNumber(serialNumber);
		}
		ProductDetailMultilingual productDetailMultilingual = ProductDetailMultilingual.builder()
				.additionalInformation(productDetailMultilingualDTO.getAdditionalInformation())
				.brandName(productDetailMultilingualDTO.getBrandName())
				.productName(productDetailMultilingualDTO.getProductName())
				.productTranslationEmbeddedId(productDetailMultilingualEmbeddedId).build();

		return productDetailMultilingual;
	}
	
	public ProductDetails getProductDetailReference(Long serialNumber) {
		return entityManager.getReference(ProductDetails.class, serialNumber);
		
	}

}
