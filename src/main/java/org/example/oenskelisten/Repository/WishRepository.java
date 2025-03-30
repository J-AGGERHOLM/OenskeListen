package org.example.oenskelisten.Repository;

import org.example.oenskelisten.Interface.IWishRepository;
import org.example.oenskelisten.Model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository extends BaseRepository implements IWishRepository {

    public WishRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public boolean createWish(Wish wish){
        String sql = "INSERT IGNORE INTO wishes " +
                "(name, description, productlink, imagelink, price, wishlistID, reserved, reserveeID)" +
                "VALUES(?,?,?,?,?,?,?,?)";

        super.getJdbc().update(sql,
                wish.getName(),
                wish.getDescription(),
                wish.getProductLink(),
                wish.getImageLink(),
                wish.getPrice(),
                wish.getWishlistID(),
                wish.isReserved(),
                wish.getReserveeID());

        return true;

    }


}
