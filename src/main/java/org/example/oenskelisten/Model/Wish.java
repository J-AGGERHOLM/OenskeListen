package org.example.oenskelisten.Model;

public class Wish {

    private int id;
    private String name;
    private String description;
    private String productLink;
    private String imageLink;
    private int price;
    private int wishlistID;
    private boolean reserved;
    private int reserveeID;
    //add once we have a wishlist model object
    //private Wishlist wishlist;



    public Wish(String name, String description, String productLink, String imageLink, int price, int wishlistID, boolean reserved, int reserveeID){
        this.name = name;
        this.description = description;
        this.productLink = productLink;
        this.imageLink = imageLink;
        this.price = price;
        this.wishlistID = wishlistID;
        this.reserved = reserved;
        this.reserveeID = reserveeID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProductLink() {
        return productLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public int getReserveeID() {
        return reserveeID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public void setReserveeID(int reserveeID) {
        this.reserveeID = reserveeID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
