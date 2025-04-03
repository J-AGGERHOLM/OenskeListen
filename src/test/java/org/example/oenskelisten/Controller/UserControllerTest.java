package org.example.oenskelisten.Controller;

import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Model.WishList;
import org.example.oenskelisten.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    // Den bliver indsat i when, når der er brug for det.
    private List<User> users;
    //private final int userId = 1;

    // Autowired laver selv en instance af mockMvc således:
    // MockMvc mockMvc = new MockMvc();
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    // Kører før hver enkelte metode. Fungere lidt som en constructor
    @BeforeEach
    void setUp() {
        users = List.of(new User(1,
                "Mathias",
                "test1@test.dk",
                "test1",
                List.of(new WishList())),
                new User(2,
                        "Claus",
                        "test2@test.dk",
                        "test2",
                        List.of(new WishList())));
    }

    @Test
    void getUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-all"))
                .andExpect(model().attributeExists("users"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void addUser() throws Exception {
        mockMvc.perform(get("/user-add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-form"))
                .andExpect(model().attributeExists("newUser"));
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(post("/{id}/delete", 1)
                        .param("id", String.valueOf(1)  ))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        verify(userService, times(1))
                .deleteUser(1);
    }


    @Test
    void deleteWishList() throws Exception{
        when(userService.getUserIDByWishListID(1)).thenReturn(1);

        mockMvc.perform(post("/wishList/{wishListID}/delete",1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/1/user"));

        verify(userService,times(1)).deleteWishList(1);
    }

    @Test
    void getHandleUser() throws Exception{

        when(userService.getUser(1)).thenReturn(users.get(0));


        mockMvc.perform(get("/{id}/edit",1)
                .param("id", String.valueOf(1)))
                .andExpect(view().name("user-edit"))
                .andExpect(model().attributeExists("user"));

        verify(userService,times(1)).getUser(1);
    }

    @Test
    void updateUser() throws Exception{

        //we need a user from our testRepo:
        //this is supposed to simulate recieving a userForm
        User mockUser = users.get(0);

        //When we call on the userService.editUser method, and it manages to reach the DB we get a succes(true)
        when(userService.editUser(any(User.class))).thenReturn(true);


        //performing the post update method with variables:
        mockMvc.perform(post("/update")
                        .param("userID", String.valueOf(mockUser.getUserID()))
                        .param("name", mockUser.getName())
                        .param("email", mockUser.getEmail())
                        .param("password", mockUser.getPassword()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));


        verify(userService, times(1)).editUser(any(User.class));

    }



    }
