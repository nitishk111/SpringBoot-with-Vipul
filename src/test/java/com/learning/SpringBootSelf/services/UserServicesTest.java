package com.learning.SpringBootSelf.services;


import com.learning.SpringBootSelf.entaties.JournalEntry;
import com.learning.SpringBootSelf.entaties.User;
import com.learning.SpringBootSelf.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

//@SpringBootTest
public class UserServicesTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Test
    @Disabled
    public void testFindByUserName(){
        Assertions.assertNotNull(userRepo.findByUserName("Ram"));
    }

//    Parameterized value passing:
//    1. CSV --> better for multiple values
//    2. ValuesSource(strings/ints ={ , , })
//    3. By imppl ArgumentProvider
    @ParameterizedTest
    @ValueSource(strings ={
            "Ram",
            "Seeta",
            "Mohan"
    })
    @Disabled
    public void testUserRecords(String name){
        Assertions.assertNotNull(userRepo.findByUserName(name), "failed for: "+name);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    @Disabled
    public void testUser(User user){
        Assertions.assertTrue(userService.saveEntry(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,5,6",
            "3,8,11",
            "2,3,7"
    })
    public void test(int a, int b, int expected){
        Assertions.assertEquals(expected, a+b);
    }

    @Disabled
    @Test
    public void testUserEntries(){
        List<JournalEntry> lst= userRepo.findByUserName("Ram").getEntries();
        Assertions.assertTrue(!lst.isEmpty());
    }

}
