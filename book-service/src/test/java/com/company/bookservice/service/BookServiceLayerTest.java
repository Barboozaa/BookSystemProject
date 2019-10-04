package com.company.bookservice.service;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.dto.Book;
import com.company.bookservice.util.feign.NoteClient;
import com.company.bookservice.util.messages.Note;
import com.company.bookservice.viewModel.BookViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceLayerTest {

    BookServiceLayer service;
    BookDao bookDao;
    RabbitTemplate template;
    NoteClient client;

    private <T> T clone(Class type, Object objectToClone) {

        Object outputObject = null;

        try {

            outputObject = type.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {

            e.printStackTrace();

        }

            List<Method> getters = Arrays.stream(objectToClone.getClass().getMethods()).filter(method -> method.getName().
                    contains("get") && !method.getName().equalsIgnoreCase("getclass"))
                    .sorted(Comparator.comparing(Method::getName)).collect(Collectors.toList());

            List<Method> setters = Arrays.stream(objectToClone.getClass().getMethods()).filter(method -> method.getName().contains("set"))
                    .sorted(Comparator.comparing(Method::getName)).collect(Collectors.toList());

        for (int i = 0; i < setters.size(); i++) {

            try {
                setters.get(i).invoke(outputObject, getters.get(i).invoke(objectToClone));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }

        return (T) outputObject;

    }

    private void setupBookDaoMock() {

        bookDao = mock(BookDao.class);
        Book bookMock = new Book();
        bookMock.setTitle("Title");
        bookMock.setAuthor("Author");

        Book inputBook = clone(Book.class, bookMock);
        bookMock.setBookId(1);

        Book bookMock2 = new Book();
        bookMock2.setTitle("Another Title");
        bookMock2.setAuthor("A Different Author");
        bookMock2.setBookId(2);

        List<Book> books = new ArrayList<>();
        books.add(bookMock);
        books.add(bookMock2);

        Optional<Book> possibleBook = Optional.ofNullable(bookMock);

        doReturn(bookMock).when(bookDao).save(inputBook);
        doReturn(possibleBook).when(bookDao).findById(1);
        doReturn(books).when(bookDao).findAll();

    }

    private void setupRabbitMock() {
        template = mock(RabbitTemplate.class);
        Note message = new Note();
        message.setBookId(1);
        message.setNote("New Note Number One");
        doNothing().when(template).convertAndSend(message);
    }

    private void setupClientMock() {
        client = mock(NoteClient.class);

        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, 1, "Note about a book"));
        notes.add(new Note(2, 1, "Another note about that book"));

        doReturn(notes).when(client).getAllNotesByBook(1);
    }

    @Before
    public void setUp() throws Exception {
        setupBookDaoMock();
        setupRabbitMock();
        setupClientMock();
        service = new BookServiceLayer(bookDao, template, client);
    }

    @Test
    public void testSaveBook() {

        BookViewModel model = new BookViewModel();

        Book book = new Book();

        book.setAuthor("Author");
        book.setTitle("Title");
        model.setBook(book);

        model = service.saveBook(model);

        assertEquals(new Integer(1), model.getBook().getBookId());
        assertEquals(2, model.getNotes().size());

    }

    @Test
    public void testFindBookById() {

        BookViewModel book = service.findById(1);

        assertEquals("Author", book.getBook().getAuthor());
        assertEquals("Title", book.getBook().getTitle());
        assertEquals(2, book.getNotes().size());

    }

    @Test
    public void testFindAllBooks() {

        List<BookViewModel> models = service.findAllBooks();

        assertEquals(2, models.size());
        assertEquals(2, models.get(0).getNotes().size());
    }

    @Test
    public void testUpdateBook() {

        BookViewModel model = service.findById(1);
        model.getBook().setAuthor("New Author For Book");
        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        doReturn(new Book()).when(bookDao).save(captor.capture());
        service.updateBook(model);

        verify(bookDao, times(1)).save(model.getBook());
        Book capturedBook = captor.getValue();
        assertEquals(model.getBook(), capturedBook);


    }

    @Test
    public void testDeleteBook() {

        ArgumentCaptor<Integer> foundId = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(bookDao).deleteById(foundId.capture());
        service.deleteBook(1);

        assertEquals(1, (int)foundId.getValue());
    }

}