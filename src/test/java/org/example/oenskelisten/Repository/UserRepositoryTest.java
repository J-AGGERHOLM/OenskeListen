package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)
@Transactional
// @Rollback(true) = default
@Rollback
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {}

    @Test
    void getAll() {
        //Arrange
        int expected = 3;

        //Act
        List<User> actual = userRepository.getAll();

        // Assert
        assertEquals(expected, actual.size());
    }

    @Test
    void getById() {
    }

    @Test
    void edit() {
    }

    @Test
    void add() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getByEmail() {
    }

    @Test
    void getUserIDByWishListID() {
    }
}