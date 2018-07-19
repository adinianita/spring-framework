package com.brainmatics;

import com.brainmatics.pos.core.Employee.Employee;
import com.brainmatics.pos.core.Product.Product;
import com.brainmatics.pos.core.Product.ProductServices;
import com.brainmatics.pos.core.Sale.Sale;
import com.brainmatics.pos.core.Sale.SaleLineItem;
import com.brainmatics.pos.core.Sale.SaleRepoNonSpring;
import com.brainmatics.pos.data.inmemory.ProductMemRepo;
import com.brainmatics.pos.data.jdbc.ProductJdbcRepo;
import com.brainmatics.pos.data.jdbc.SaleJdbcRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		//ApplicationContext ctx = SpringApplication.run(PosApplication.class, args);
		//ProductServices service =ctx.getBean(ProductServices.class);
		//	System.out.println(service.generateCode());

		SpringApplication.run(PosApplication.class, args);


	}

	@Autowired
	JdbcTemplate jdbc;

	public void initDb(){
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

		Sale sale = new Sale();
		sale.setCashier(e2);
		sale.setId(102);
		sale.addLineItem(p2,2);
		sale.addLineItem(p3,1);

		SaleRepoNonSpring saleRepo = new SaleJdbcRepo(jdbc);
		saleRepo.save(sale);



	}

    @Override
	public void run(String... args)throws Exception{
		//jdbc.execute("CREATE TABLE EMPLOYEE (id int primary key, name varchar(50)) ");
		//jdbc.execute("INSERT INTO EMPLOYEE (id, name) VALUES  (1, 'Michael Suyama')");
        List<Employee> emps = jdbc.query("SELECT id, name FROM Employee", (rs, num) ->{
            Employee e = new Employee();
            e.setId(rs.getInt("id"));
            e.setName(rs.getString("name"));
            return e;
        });

        for(Employee e:emps){
          //  System.out.println(e.getName());
        }

        ProductJdbcRepo repo = new ProductJdbcRepo(jdbc);
        for (Product p: repo.getAll()){
           // System.out.println(p.getCcde());

        }

        SaleJdbcRepo saleRepo = new SaleJdbcRepo(jdbc);
        Sale s = saleRepo.getByIdEager(1);
        for(SaleLineItem sli: s.getLineItems()){
            //System.out.println(sli.getProduct().getName());
        }

       // initDb();
	}


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
