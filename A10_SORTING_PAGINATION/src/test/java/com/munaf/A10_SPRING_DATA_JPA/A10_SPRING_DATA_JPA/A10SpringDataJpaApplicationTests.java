package com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA;

import com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.entities.ProductEntity;
import com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class A10SpringDataJpaApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void testRepository() {
//		ProductEntity productEntity = ProductEntity.builder()
//				.sku("dairy@122")
//				.name("DairyMilk")
//				.price(BigDecimal.valueOf(150.75))
//				.quantity(50)
//				.build();
//
//		ProductEntity savedProductEntity = productRepository.save(productEntity);
//		System.out.println(savedProductEntity);
//	}

	@Test
	void getRepository(){
		List<ProductEntity> entities = productRepository.findAll();
		System.out.println(entities);
	}

	// custom default queries
	@Test
	void getRepository2(){
		Optional<ProductEntity> entitiy = productRepository.findByName("Chips");
		System.out.println("CHIPS DETAILS : ");

		String available = entitiy.map(e -> e.toString())
				.orElse("Entity not found");

		System.out.println(available);
	}

	@Test
	void getRepository3(){
//		List<ProductEntity> products = productRepository.findByCreatedAtAfter(
//				LocalDateTime.of(2024,12,26,0,0,0)
//		);

//		List<ProductEntity> products = productRepository.findByNameAndPriceLessThan("Pepsi", BigDecimal.valueOf(70));

//		List<ProductEntity> products = productRepository.findByNameIgnoreCaseContains("pep");

//		List<ProductEntity> products = productRepository.findByNameAnsSku("Parle", "parle123");

//		Integer products = productRepository.countByPriceLessThan(BigDecimal.valueOf(100));

//		Integer products = productRepository.deleteByPrice(BigDecimal.valueOf(174.25));

		List<ProductEntity> products = productRepository.findAllByName("DairyMilk");

		System.out.println(products);
	}



}
