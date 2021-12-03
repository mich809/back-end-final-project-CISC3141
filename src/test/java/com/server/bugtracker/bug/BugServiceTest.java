package com.server.bugtracker.bug;

import com.server.bugtracker.user.User;
import com.server.bugtracker.user.UserRepo;
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
class BugServiceTest
{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BugRepo bugRepo;

    @Test
    @Rollback(false)
    void test_getBugs()
    {
        // Get initial number of Bugs in database
        final int initialSize = bugRepo.getBugs().size();

        // Create a new user
        User user1 = new User( "Joe Public", "tester2", "test",
                "test@email.com", "DBA", "N/A" );
        userRepo.save(user1);

        // Create bug entries
        Bug bug1 = new Bug("Test bug1", "Test bug1", "2020-01-01",
                1, 1, "LOW", "OPEN");
        Bug bug2 = new Bug("Test bug2", "Test bug2", "2021-09-13",
                1, 1, "HIGH", "OPEN");
        Bug bug3 = new Bug("Test bug3", "Test bug3", "2021-11-20",
                1, 1, "MEDIUM", "CLOSED");
        bugRepo.save(bug1);
        bugRepo.save(bug2);
        bugRepo.save(bug3);

        // Get current number of Bug entries
        final int currentSize = bugRepo.getBugs().size();

        Assertions.assertEquals( initialSize + 3, currentSize );

    }

    @Test
    @Rollback(false)
    void test_createBug()
    {
        // Create Bug entry
        Bug bug = new Bug("Test bug1", "Test bug description",
                "2021-04-10", 1, 2, "MEDIUM", "CLOSED");
        Bug result = bugRepo.save(bug);

        Assertions.assertNotNull( result );

    }


}