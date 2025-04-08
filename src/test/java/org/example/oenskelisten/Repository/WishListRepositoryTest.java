package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Model.WishList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)
@Transactional
// Rollback = true er default
@Rollback
class WishListRepositoryTest {
    private List<WishList> actualWishList;

    @Autowired
    private WishListRepository wishListRepository;

    @BeforeEach
    void setUp() {
        actualWishList = wishListRepository.getAll();
    }

    @Test
    void getAll() {
        // Arrange
        int expected = 3;

        // Assert
        assertEquals(expected, actualWishList.size());
    }
    @Sql(
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"classpath:h2initNoData.sql"}
    )
    @Test
    void getAll_expected_null() {
        // Arrange
        List<WishList> expected = new ArrayList<>();

        // Assert
        assertEquals(expected, actualWishList);
    }

    @Test
    void getById() {
        // Arrange
        var expected = new WishList(1, "Alices Birthday List", 1);

        // Act
        var actual = wishListRepository.getById(1);

        // Assert
        assertEquals(expected, actual);
    }
    @Test
    void getById_excepted_null() {
        // Arrange
        WishList expected = null;

        // Act
        var actual = wishListRepository.getById(10);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        // Arrange
        var expected =  new WishList(4, "Alices Birthday List", 1);

        // Act
        var actual = getWishList(expected);

        // Assert
        assertEquals(expected, actual);
    }
    @Test
    void add_wrong_id() {
        // Arrange
        var wishList =  new WishList(5, "Alices Birthday List", 1);
        WishList expected = null;

        // Act
        var actual = getWishList(wishList);

        // Assert
        assertEquals(expected, actual);
    }

    private WishList getWishList(WishList wishList) {
        wishListRepository.add(wishList);
        return wishListRepository.getById(wishList.getId());
    }

    @Test
    void deleteById() {
        // Arrange
        var wishlist = new WishList(1, "Alices Birthday List", 1);
        WishList expected = null;

        // Act
        wishListRepository.deleteById(wishlist.getId());
        var actual = wishListRepository.getById(wishlist.getId());

        assertEquals(expected, actual);
    }
}