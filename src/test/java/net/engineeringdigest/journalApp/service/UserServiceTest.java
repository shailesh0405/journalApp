package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
@Disabled
//@BeforeEach
//@BeforeAll
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
//        User user = userRepository.findByUserName("ram");
//        assertTrue(!user.getJournalEntries().isEmpty());
        assertTrue(userService.saveNewUser(user));

//        assertEquals(4,2+2);
//        assertNotNull(userRepository.findByUserName("shailesh"));
    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "4,2,2",
            "2,1,1",
            "5,4,1"

    })
//@ValueSource(ints = {1,2,3,4})
//    @EnumSource
//    @ArgumentsSource()
    public void test(int expected,int a,int b){
        assertEquals(expected,a+b);
    }
}
