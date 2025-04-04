package org.example.oenskelisten.Controller;

import org.example.oenskelisten.Model.Wish;
import org.example.oenskelisten.Service.WishListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishListController.class)
class WishListControllerTest {
    // objekt, som kan indsætte i when kaldet.
    private Wish wish;
    private int mockId;

    // Autowired laver selv en instance af mockMvc således:
    // MockMvc mockMvc = new MockMvc;
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishListService wishListService;

    @BeforeEach
    void setUp() {
        wish = new Wish();
        mockId = 1;
    }


    @Test
    void editWish() throws Exception {
        when(wishListService.getWishById(1)).thenReturn(wish);

        mockMvc.perform(get("/wishlist/{id}/edit", mockId))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-wish"))
                .andExpect(model().attributeExists("wish"));

        verify(wishListService).getWishById(1);

    }

    @Test
    void testEditWish() throws Exception {
        mockMvc.perform(post("/wishlist/update")
                        .param("id", String.valueOf(mockId))
                        .param("name", "ønske1")
                        .param("description", "en beskrivelse")
                        .param("productLink", "/forestiller/et/link")
                        .param("imageLink", "/forestiller/et/link")
                        .param("price", String.valueOf(mockId))
                        .param("wishListID", String.valueOf(mockId))
                        .param("reserved", String.valueOf(true))
                        .param("reerveeID", String.valueOf(mockId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist/list/" + -1));

        // any(Wish.class) betyder:
        // den bare skal tage imod hvilken som helst Wish object.
        verify(wishListService).updateWish(any(Wish.class));
    }
}