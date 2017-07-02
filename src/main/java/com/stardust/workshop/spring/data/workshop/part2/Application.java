package com.stardust.workshop.spring.data.workshop.part2;

import com.stardust.workshop.spring.data.workshop.part2.entities.Address;
import com.stardust.workshop.spring.data.workshop.part2.repository.AddressRepository;
import com.stardust.workshop.spring.data.workshop.part2.repository.DataRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = "com.stardust.workshop.spring.data.workshop")
public class Application {

	public static void main(String[] args) {
		ApplicationContext context
				= new AnnotationConfigApplicationContext(Application.class);

		DataRepository<Address> addressRepository = context.getBean("addressRepository", DataRepository.class);
		addressRepository.removeAll();
		addressRepository.createAll();
		for (Address address : addressRepository.queryAll()) {
			System.out.println("****** Print record ******");
			System.out.println(address);
		}
	}
}
