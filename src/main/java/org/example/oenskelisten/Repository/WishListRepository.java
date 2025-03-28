package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository extends BaseRepository {
    public WishListRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public List<Wish> getAllWishListItems() {
        return null;
    }

    public Wish getWishById(int id) {
        return null;
    }

    public void addWish(Wish wish) {

    }

    public void updateWish(Wish wish) {

    }

    public Wish getWishByName(String name) {
        return null;
    }

    public void deleteWishById(int id) {

    }
}
