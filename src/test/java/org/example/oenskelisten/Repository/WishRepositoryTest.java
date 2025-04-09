package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Model.Wish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)
@Transactional
// Rollback = true er default
@Rollback

public class WishRepositoryTest {
    @Autowired
    private WishRepository wishRepository;

    @Test
    void getAll(){
        int expected = 6;

        var actual = wishRepository.getAll();

        assertEquals(expected, actual.size());
    }

    @Test
    void getById() {
        //test id
        int id = 2;
        Wish expected = new Wish(id,"Gaming Laptop","High-end gaming laptop with RTX 4080",
                        "https://example.com/gaming-laptop","https://example.com/gaming-laptop.jpg", 2000,
                        2, true,1);

        Wish actual = wishRepository.getById(id);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.isReserved(), actual.isReserved());
    }

    @Test
    void deleteById() {
        //id to be tested on:
        int id = 3;

        //checks to see if wish is present before deleting
        Optional<Wish> deletedWish = Optional.ofNullable(wishRepository.getById(id));
        assertTrue(deletedWish.isPresent());


        //checks size, deletes wish, check size again
        int expectedCount = wishRepository.getAll().size();
        wishRepository.deleteById(id);
        int actualCount = wishRepository.getAll().size();
        assertEquals(expectedCount -1, actualCount);

        //checks to see if wish is present after deletion
        deletedWish = Optional.ofNullable(wishRepository.getById(id));
        assertFalse(deletedWish.isPresent());
    }
}
