package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import  org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class UserDetailServiceTest {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadUserDetailsTest() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("dsfasdfasd").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsServiceImpl.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
