package com.accio.librarymanagementsystem;

import com.accio.librarymanagementsystem.Entity.Author;
import com.accio.librarymanagementsystem.Repositories.AuthorRepository;
import com.accio.librarymanagementsystem.Services.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LibraryManagementSystemApplicationTests {

	@Mock
	public AuthorRepository authorRepository;

	@InjectMocks
	public AuthorService authorService; //this is also automatically creating an object of the service layer
	//AuthorService authorService = new AuthorService();


	@Test
	public void testMaxBooksAuthor() {
		Author a1 = new Author(1, 3);
		Author a2 = new Author(2, 5);
		Author a3 = new Author(3, 7);

		List<Author> authorList = new ArrayList<>();
		authorList.add(a1);
		authorList.add(a2);
		authorList.add(a3);

		Mockito.when(authorRepository.findAll()).thenReturn(authorList);
		Author actualAuthor = authorService.getAuthorWithMaxBooks();
		Assertions.assertEquals(actualAuthor, a3);
	}

}
