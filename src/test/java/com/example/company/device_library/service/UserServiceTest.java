package com.example.company.device_library.service;

import com.example.company.device_library.model.User;
import com.example.company.device_library.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    private User user;

    @Before
    public void initialize() {
        user = new User();
        user.setId(1L);
        user.setFirstName("UserFirstName");
        user.setLastName("UserLastName");
        user.setEmail("user@com.pl");
        user.setLocalization("User workplace");
        user.setActive(true);
        user.setPosition("User work position");
    }

    @After
    public void cleanDatabase() {
        userRepository.deleteAll();
        userRepository.flush();
    }

    @Test
    public void shouldInsertUserToDatabase() {
        Collection<User> userCollection = userRepository.findUsersByLastName("UserLastName");
        int collectionSize = userCollection.size();

        userRepository.save(user);
        Optional<User> user1 = userRepository.findUserByUsernameAsEmail("user@com.pl");
        assertThat(user1.get().getId().longValue()).isNotEqualTo(0);

        userCollection = userRepository.findUsersByLastName("UserLastName");
        assertThat(userCollection.size()).isEqualTo(collectionSize + 1);
    }

    @Test
    public void shouldUpdateUserInformation(){
        User user = userRepository.save(this.user);

        Optional<User> userBeforeChange = userRepository.findUserById(1L);
        userBeforeChange.get().setPassword("newPassword");
        userBeforeChange.ifPresent(u -> userRepository.save(user));

        assertThat(user.getPassword()).isEqualTo("newPassword");
    }

    @Test
    public void shouldNotFindUserByLastName() {
        Collection<User> userCollection =
                userRepository.findUsersByLastName("UserLastName");
        assertThat(userCollection.isEmpty()).isTrue();
    }

}