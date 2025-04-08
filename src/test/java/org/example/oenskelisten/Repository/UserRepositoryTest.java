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
        // Arrange

        User newUser = new User();
        newUser.setName("mr. man");
        newUser.setEmail("man@example.com");
        newUser.setPassword("password");

        //Act
        userRepository.add(newUser);
        List<User> users = userRepository.getAll();
        User addedUser = null;
        for(User u : users){
            if (u.getEmail().equals("man@example.com")){
                addedUser=u;
            }
        }

        //Assert
        assertEquals(4, users.size());
        assertEquals("mr. man", addedUser.getName());

    }

    @Test
    void deleteById() {
        // Arrange
        int userIdToDelete = 3;

        //Act
        userRepository.deleteById(userIdToDelete);
        List<User> users = userRepository.getAll();

        //Assert

        assertEquals(2, users.size());

    }

    @Test
    void getByEmail() {
        // Arrange
        String email = "bob@example.com";

        //Act
        User user = userRepository.getByEmail(email);


        //Assert
        assertNotNull(user);
        assertEquals("Bob Smith", user.getName());
        assertEquals("securepass", user.getPassword());

    }

    @Test
    void getUserIDByWishListID() {
        // Arrange
        int wishlistID = 2;
        //Act
        int userID = userRepository.getUserIDByWishListID(wishlistID);

        //Assert
        assertEquals(2, userID);

    }
}