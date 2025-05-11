package com.ezpz.ecom.ezstore.product.operation.service.impl;
import com.ezpz.ecom.ezstore.product.operation.util.OperationUtil;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezpz.ecom.ezstore.product.common.entities.Product;
import com.ezpz.ecom.ezstore.product.common.entities.ProductDetailMultilingual;
import com.ezpz.ecom.ezstore.product.common.entities.ProductDetails;
import com.ezpz.ecom.ezstore.product.common.repo.ProductDetailMultilingualRepo;
import com.ezpz.ecom.ezstore.product.common.repo.ProductDetailsRepo;
import com.ezpz.ecom.ezstore.product.common.repo.ProductRepository;
import com.ezpz.ecom.ezstore.product.common.util.APIResponse;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDTO;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDetailMultilingualDTO;
import com.ezpz.ecom.ezstore.product.operation.dto.ProductDetailsDTO;
import com.ezpz.ecom.ezstore.product.operation.service.ProductDashboardService;

@Service
public class ProductDashboardServiceImpl implements ProductDashboardService {

	@Autowired
	OperationUtil operationUtil;


	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDetailsRepo productDetailsRepo;
	
	@Autowired
	private ProductDetailMultilingualRepo productDetailMultilingualRepo;

    
	
	@Override
	public ProductDTO getProductById(Long id) {
		return new ProductDTO();
	}

	@Override
	public APIResponse addProduct(ProductDTO productDTO) {
		Product product=OperationUtil.convertProductDTOtoEntity(productDTO);
		APIResponse apiResponse=new APIResponse();
		try {
			
			if(productRepository.save(product)!=null){
				apiResponse.setMessage("Successfully added product data.");
				apiResponse.setStatus("201");
			}
			else {
				System.out.print("failed to save data");
				apiResponse.setMessage("Failed to add product data.");
				apiResponse.setStatus("500");
			}
		}
		catch(Exception e) {

			apiResponse.setMessage("Failed to add product data.");
			apiResponse.setStatus("500");
			System.out.print("failed to save data"+ e.getMessage());
		}
		
		// TODO Auto-generated method stub
		return apiResponse;
	}

	@Override
	public APIResponse addProductDetail(ProductDetailsDTO productDetailsDTO, Long productId) {
		ProductDetails productDetails=OperationUtil.convertProductDetailsDTOtoEntity(productDetailsDTO, productId);
		APIResponse apiResponse=new APIResponse();
		try {
			
			if(productDetailsRepo.save(productDetails)!=null){
				apiResponse.setMessage("Successfully added product data.");
				apiResponse.setStatus("201");
			}
			else {
				System.out.print("failed to save data");
				apiResponse.setMessage("Failed to add product data.");
				apiResponse.setStatus("500");
			}
		}
		catch(ConstraintViolationException e ) {
			apiResponse.setMessage("Failed to add product data.");
			apiResponse.setStatus("202");
			System.out.print("data present with ame serial number");
		}
		catch(Exception e) {

			apiResponse.setMessage("Failed to add product data.");
			apiResponse.setStatus("500");
			System.out.print("failed to save data");
		}
		
		// TODO Auto-generated method stub
		return apiResponse;
	}

	@Override
	public APIResponse addProductDetailMultilingual(ProductDetailMultilingualDTO productDetailMultilingualDTO,
			Long serialNumber) {
		ProductDetailMultilingual productDetailMultilingual=OperationUtil.convertProductDetailMultilingualDTOtoEntity(productDetailMultilingualDTO, serialNumber);
		//getting reference of parent entity to set it in child because we are using mapsId which require parent to provide foriegn key
		ProductDetails pd= operationUtil.getProductDetailReference(serialNumber);
		productDetailMultilingual.setProductDetails(pd);
			APIResponse apiResponse=new APIResponse();
			try {
				
				if(productDetailMultilingualRepo.save(productDetailMultilingual)!=null){
					apiResponse.setMessage("Successfully added product data.");
					apiResponse.setStatus("201");
				}
				else {
					System.out.print("failed to save data");
					apiResponse.setMessage("Failed to add product data.");
					apiResponse.setStatus("500");
				}
			}
			catch(ConstraintViolationException e ) {
				apiResponse.setMessage("Failed to add product data.");
				apiResponse.setStatus("202");
				System.out.print("data present with ame serial number");
			}
			catch(Exception e) {

				apiResponse.setMessage("Failed to add product data.");
				apiResponse.setStatus("500");
				System.out.print("failed to save data");
			}
			
			// TODO Auto-generated method stub
			return apiResponse;
	}

}
