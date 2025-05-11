package com.ezpz.ecom.ezstore.product.operation.dto;

import java.util.Map;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDetailMultilingualDTO {

	private String languageCode;
	private String productName; 
	private String brandName;
	private Map<String,Map<String,String>> additionalInformation;
	
}
