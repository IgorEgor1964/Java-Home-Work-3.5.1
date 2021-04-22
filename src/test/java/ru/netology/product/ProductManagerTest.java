package ru.netology.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Book book1 = new Book(1, "Акванавты", 300, "Сергей Павлов");
    Book book2 = new Book(2, "День триффидов", 400, "Джон Уиндем");
    Book book3 = new Book(3, "Хроники Амбера", 500, "Роджер Желязны");
    Smartphone phone1 = new Smartphone(4, "Huawei 7Y", 10000, "Huawei");
    Smartphone phone2 = new Smartphone(5, "Honor 20", 20000, "Honor");
    Smartphone phone3 = new Smartphone(6, "Nokia 8", 30000, "Nokia");

    @BeforeEach
    void setUp() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
    }

    @Test
    void shouldFindBookByNameIfExists() {
        String textToFind = "Акванавты";
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByNameIfNoExists() {
        String textToFind = "Бродяги севера";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBookByAuthorIfExists() {
        String textToFind = "Джон Уиндем";
        Product[] expected = new Product[]{book2};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByAuthorIfNoExists() {
        String textToFind = "Беляев";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByNameIfExists() {
        String textToFind = "Nokia 8";
        Product[] expected = new Product[]{phone3};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByNameIfNoExists() {
        String textToFind = "Sony";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByProducerIfExists() {
        String textToFind = "Honor";
        Product[] expected = new Product[]{phone2};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByProducerIfNoExists() {
        String textToFind = "Samsung";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenMissingProduct() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("нет такого продукта");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByOneProduct() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("book1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByPhoneAndBook() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("book1, phone2");
        assertArrayEquals(expected, actual);

    }
}
