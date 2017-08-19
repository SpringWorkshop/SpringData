package com.stardust.workshop.spring.data.workshop.part4;

import com.stardust.workshop.spring.data.workshop.part4.entities.Author;
import com.stardust.workshop.spring.data.workshop.part4.entities.Book;
import com.stardust.workshop.spring.data.workshop.part4.repositories.AuthorRepository;
import com.stardust.workshop.spring.data.workshop.part4.repositories.BookRepository;
import com.stardust.workshop.spring.data.workshop.part4.repositories.DataRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ComponentScan(basePackages = "com.stardust.workshop.spring.data.workshop")
public class Application {

	public static void main(String[] args) {
		ApplicationContext context
				= new AnnotationConfigApplicationContext(Application.class);

		BookRepository bookRepository = context.getBean(BookRepository.class);
		AuthorRepository authorRepository = context.getBean(AuthorRepository.class);

		Author author = new Author();
		author.setName("Foo");
		author.setGender("M");
		authorRepository.save(author);


		Book bookA = new Book();
		Book bookB = new Book();
		bookA.setName("Book A");
		bookB.setName("Book B");
		bookA.setAuthor(author);
		bookB.setAuthor(author);
		bookRepository.save(bookA);
		bookRepository.save(bookB);

		Author author1 = authorRepository.find(1);
		List<Book> books = author1.getBooks();
		System.out.println(books.size());
		Map<String, Object> filters = new HashMap<>();
		filters.put("name", "Book B");
		List<?> results = bookRepository.query(filters);
		filters.clear();
		filters.put("author.name", "Foo");
		results = bookRepository.query(filters);
		int a=0;
	}
}
