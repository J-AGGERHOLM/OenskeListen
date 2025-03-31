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




    public Wish(int id,
                String name,
                String description,
                String productLink,
                String imageLink,
                int price,
                int wishlistID,
                boolean reserved,
                int reserveeID){

        this.id = id;
        this.name = name;
        this.description = description;
        this.productLink = productLink;
        this.imageLink = imageLink;
        this.price = price;
        this.wishlistID = wishlistID;
        this.reserved = reserved;
        this.reserveeID = reserveeID;
    }

    public Wish(){
        this.id = -1;
        this.name = null;
        this.description = null;
        this.productLink = null;
        this.imageLink = null;
        this.price = -1;
        this.wishlistID = -1;
        this.reserved = false;
        //change back to minus 1 after were done testing
        this.reserveeID = 1;
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

    public String getReserveeIDAsString(){
        if(!reserved){
            return null;
        }else{
            return Integer.toString(reserveeID);
        }
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
