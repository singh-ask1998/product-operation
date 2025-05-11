package com.ezpz.ecom.ezstore.product.operation.dto;

import java.util.List;
import java.util.UUID;

import com.ezpz.ecom.ezstore.product.common.model.Price;
import com.ezpz.ecom.ezstore.product.common.model.ProductInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class ProductDTO {

	private Long productId;
	private UUID uuid;
	private String productIdentifier;
	private Long categoryId;
	private List<ProductDetailsDTO> productDetailsDTO;
	private String status;
	private String tags;
	
	
}
