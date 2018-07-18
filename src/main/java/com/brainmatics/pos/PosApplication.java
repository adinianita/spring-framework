package com.brainmatics.pos;

import com.brainmatics.pos.core.Product;
import com.brainmatics.pos.core.ProductServices;
import com.brainmatics.pos.infra.data.inmemory.ProductMemRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootApplication
public class PosApplication {

	public static void mainManual(String[] args) {
		Product p1 = new Product();
		p1.setId(1);
		p1.setName("Momogi");
		p1.setPrice(BigDecimal.valueOf(500));

		Product p2 = new Product();
		p2.setId(2);
		p2.setName("Pepsi");
		p2.setPrice(BigDecimal.valueOf(5_000));

		Product p3 = new Product();
		p3.setId(3);
		p3.setName("Ayam");
		p3.setPrice(BigDecimal.valueOf(50_000));

		ProductMemRepo repo = new ProductMemRepo();
		repo.save(p1);
		repo.save(p2);
		repo.save(p3);

		Product prd2 = repo.getById(2);
		//System.out.println(prd2.getName());

		ArrayList<Product> all = repo.getAll();
		for(Product p: all){
			//System.out.println(p.getName());
		}

		ProductServices service = new ProductServices(new ProductMemRepo());
		//System.out.println(service.generateCode());



	}

	public static void main(String[] args) {
		//ApplicationContext ctx = SpringApplication.run(PosApplication.class, args);
		//ProductServices service =ctx.getBean(ProductServices.class);
	//	System.out.println(service.generateCode());

        SpringApplication.run(PosApplication.class, args);

	}

	/* public static void main1(String[] args) {
		Employee e1 = new Employee();
		e1.setId(1);
		e1.setName("Michael Suyama");
		e1.setBirthdate(LocalDate.of(1993,10,23));


		Employee e2 = new Employee();
		e2.setId(2);
		e2.setName("Nancy");
		e2.setBirthdate(LocalDate.of(1960,2,23));

		Product p1 = new Product();
		p1.setId(1);
		p1.setName("Momogi");
		p1.setPrice(BigDecimal.valueOf(500));

		Product p2 = new Product();
		p2.setId(2);
		p2.setName("Pepsi");
		p2.setPrice(BigDecimal.valueOf(5_000));

		Product p3 = new Product();
		p3.setId(3);
		p3.setName("Ayam");
		p3.setPrice(BigDecimal.valueOf(50_000));

		Sale s1 = new Sale();
		s1.setId(1);
		s1.setCashier(e1);
		s1.getProducts().add(p1);
		s1.getProducts().add(p2);
		s1.getProducts().add(p3);

		for (Product p : s1.getProducts()){
			System.out.println(p.getName());
		}

		System.out.println(s1.getTotal());

		Sale s2 = new Sale();
		s2.setId(1);
		s2.setCashier(e2);


		ArrayList<Employee> nama = new ArrayList<>();
		nama.add(e1);
		nama.add(e2);

		SpringApplication.run(PosApplication.class, args);
	}*/


}
