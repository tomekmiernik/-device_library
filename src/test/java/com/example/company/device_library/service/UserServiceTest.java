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

    private User userDto;

    @Before
    public void initialize() {
        userDto = new User();
        userDto.setFirstName("UserFirstName");
        userDto.setLastName("UserLastName");
        userDto.setEmail("user@com.pl");
        userDto.setLocalization("User workplace");
        userDto.setActive(true);
        userDto.setPosition("User work position");
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

        userRepository.save(userDto);
        Optional<User> user1 = userRepository.findUserByUsernameAsEmail("user@com.pl");
        assertThat(user1.get().getId().longValue()).isNotEqualTo(0);

        userCollection = userRepository.findUsersByLastName("UserLastName");
        assertThat(userCollection.size()).isEqualTo(collectionSize + 1);
    }

    @Test
    public void shouldNotFindUserByLastName() {
        Collection<User> userCollection =
                userRepository.findUsersByLastName("UserLastName");
        assertThat(userCollection.isEmpty()).isTrue();
    }

}