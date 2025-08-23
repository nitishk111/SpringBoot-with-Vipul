package com.learning.SpringBootSelf.services;

import com.learning.SpringBootSelf.entaties.JournalEntry;
import com.learning.SpringBootSelf.entaties.User;
import com.learning.SpringBootSelf.repository.JournalEntryRepo;
import com.learning.SpringBootSelf.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.*;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserServiceTest2 {

    @InjectMocks
    private UserService service;

    @Mock
    UserRepo entryRepo;

    @BeforeEach
    @Disabled
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled
    void  getById(){
        when(entryRepo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(User.builder().userName("Raul").password("234").build()));

        Assertions.assertNotNull(entryRepo.findById(23L).orElseGet(() -> null));
    }
}
