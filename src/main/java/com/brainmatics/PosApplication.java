package com.brainmatics;

import com.brainmatics.pos.core.Employee.Employee;
import com.brainmatics.pos.core.Employee.EmployeeRepo;
import com.brainmatics.pos.core.Product.Product;
import com.brainmatics.pos.core.Product.ProductRepo;
import com.brainmatics.pos.core.Product.ProductServices;
import com.brainmatics.pos.core.Sale.Sale;
import com.brainmatics.pos.core.Sale.SaleRepo;
import com.brainmatics.pos.infra.data.inmemory.ProductMemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class PosApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbc;
	@Autowired
	ProductRepo productrepo;
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	SaleRepo saleRepo;


	public static void main(String[] args) {
		//ApplicationContext ctx = SpringApplication.run(PosApplication.class, args);
		//ProductServices service =ctx.getBean(ProductServices.class);
		//	System.out.println(service.generateCode());

		SpringApplication.run(PosApplication.class, args);
	}

	public void initDb(){
		Product p1 = new Product();
		p1.setId(1);
		p1.setCcde("P01");
		p1.setName("Momogi");
		p1.setPrice(BigDecimal.valueOf(500));

		Product p2 = new Product();
		p2.setId(2);
		p2.setCcde("P02");
		p2.setName("Pepsi");
		p2.setPrice(BigDecimal.valueOf(5_000));

		Product p3 = new Product();
		p3.setId(3);
		p3.setCcde("P03");
		p3.setName("Ayam");
		p3.setPrice(BigDecimal.valueOf(50_000));

		productrepo.save(p1);
		productrepo.save(p2);
		productrepo.save(p3);

		Employee e1 = new Employee();
		e1.setId(1);
		e1.setName("Michael Suyama");
		e1.setBirthdate(LocalDate.of(1993,10,23));

		Employee e2 = new Employee();
		e2.setId(2);
		e2.setName("Nancy");
		e2.setBirthdate(LocalDate.of(1960,2,23));

		employeeRepo.save(e1);
		employeeRepo.save(e2);

		Sale sale1 = new Sale();
		sale1.setCashier(e2);
		sale1.setId(200);
		sale1.addLineItem(p1,2);
		sale1.addLineItem(p3,1);

		Sale sale2 = new Sale();
		sale2.setCashier(e1);
		sale2.setId(201);
		sale2.addLineItem(p2,2);
		sale2.addLineItem(p3,1);

		Sale sale3 = new Sale();
		sale3.setCashier(e2);
		sale3.setId(202);
		sale3.addLineItem(p2,4);
		sale3.addLineItem(p3,1);

		Sale sale4 = new Sale();
		sale4.setCashier(e1);
		sale4.setId(203);
		sale4.addLineItem(p2,5);
		sale4.addLineItem(p3,1);

		saleRepo.save(sale1);
		saleRepo.save(sale2);
		saleRepo.save(sale3);
		saleRepo.save(sale4);

		//SaleRepoNonSpring saleRepo = new SaleJdbcRepo(jdbc);
		//saleRepo.save(sale); */
	}

    @Override
	public void run(String... args)throws Exception{
		/*saleRepo.findById(200).ifPresent(s -> {
			for (SaleLineItem sli: s.getLineItems()){
				System.out.println(sli.getQuantitiy());
			}
		});*/

		int page =1;
		Page<Sale> salePages =  saleRepo.findAll(PageRequest.of(1,2));
		System.out.println("Total: "+salePages.getTotalElements());
		System.out.printf("Page %d of %d\n",page,salePages.getTotalPages());
		salePages.forEach(s -> {
			System.out.println(s.getId());
		});

		//initDb();
		//saleRepo.findById(200).ifPresent(s -> System.out.println(s.getId()));
		//saleRepo.findById(200).ifPresent(s -> System.out.println(s.getCashier().getName()));

		//jdbc.execute("CREATE TABLE EMPLOYEE (id int primary key, name varchar(50)) ");
		//jdbc.execute("INSERT INTO EMPLOYEE (id, name) VALUES  (1, 'Michael Suyama')");
      /*  List<Employee> emps = jdbc.query("SELECT id, name FROM Employee", (rs, num) ->{
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
        }*/
	}

	public void howToUseOptional(){
		Optional<Product> opt =  productrepo.findById(10);
		opt.map(p -> p.getPrice().multiply(BigDecimal.valueOf(2)))
				.isPresent();

		opt.map(p -> {
			System.out.println(p.getName());
			return 0;
		});

		Product prod = opt.orElse(new Product());
		System.out.println(prod.getName());

		opt.ifPresent(product -> System.out.println(product.getName()));
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
