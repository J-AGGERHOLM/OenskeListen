package org.example.oenskelisten.Service;

import org.example.oenskelisten.Model.User;
import org.example.oenskelisten.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;


    @BeforeEach
    void setUp() {
        user = new User(1,"test","test@test.dk","1234");
    }

    @Test
    void getUser(){
        //Arrange
        when(userRepository.getById(user.getUserID())).thenReturn(user);
        //Act
        var result = userService.getUser(user.getUserID());
        //Assert
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository).getById(user.getUserID());
    }

    @Test
    void editUser() {
        // Arrange
        when(userRepository.getById(1)).thenReturn(user);


        // Act
        var result = userService.editUser(user);

        // Assert
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void deleteUser() {
        when(userRepository.getById(1)).thenReturn(user);
        userService.deleteUser(1);
        verify(userRepository).deleteById(1);
    }
}