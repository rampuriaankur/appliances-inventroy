package com.cg.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>, CrudRepository<Product, Integer> {

	@Query("select p from Product p where upper(p.serialNumber)=upper(?1) and upper(p.brand) = upper(?2) and upper(p.model) = upper(?3)")
	Product getProductBySerialNumberWithBrandWithModel(String serialNumber, String brand, String model);

	
	@Query("select p1 from Product p1 where " + "(?1 is null or upper(p1.serialNumber)= upper(?1))"
			+ "and (?2 is null or upper(p1.brand) = upper(?2))"
			+ "and (?3 is null or upper(p1.model) = upper(?3))"
			+ "and (?4 is null or upper(p1.status) = upper(?4))"
			+ "and (?5 is null or p1.purchaseDate = ?5)")			
	List<Product> getListOfAllMatchingProduct( String serialNumber,  String brand , String model, String status ,Date purchaseDate);


	List<Product> findByPurchaseDate(java.util.Date purchaseDate);
			 
}
