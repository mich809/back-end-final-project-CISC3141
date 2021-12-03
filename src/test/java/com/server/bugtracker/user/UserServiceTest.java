package com.server.bugtracker.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceTest
{

    @Autowired
    private UserRepo userRepo;

    @Test
    @Rollback(false)
    void test_getUsers()
    {
        // Get initial number of Users in database
        final int initialSize = userRepo.getUsers().size();

        // Create Users entries
        User user1 = new User( "Joe Public", "tester2", "test",
                "test@email.com", "DBA", "N/A" );
        User user2 = new User( "Jane Doe", "tester3", "test",
                "test@email.com", "DBA", "N/A" );
        User user3 = new User( "John Doe", "tester4", "test",
                "test@email.com", "DBA", "N/A" );
        User user4 = new User( "Jane Doe", "tester5", "test",
                "test@email.com", "DBA", "N/A" );
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);

        // Get current number of User entries
        final int currentSize = userRepo.getUsers().size();

        Assertions.assertEquals(initialSize + 4, currentSize );
    }
}