package com.ezpz.ecom.ezstore.product.operation.dto;

import java.util.List;
import java.util.Map;

import com.ezpz.ecom.ezstore.product.common.model.Price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDTO {
      
	private Long serialNumber;
	private Map<String,Object> variationAttribute;
	private Price price;
	private Long offerId;
	private List<ProductDetailMultilingualDTO> productDetailMultilingualDTO;
}
