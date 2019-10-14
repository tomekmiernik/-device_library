package com.example.company.device_library.service;

import com.example.company.device_library.model.UserApp;
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
public class UserAppServiceTest {

    @Autowired
    UserRepository userRepository;

    private UserApp userApp;

    @Before
    public void initialize() {
        userApp = new UserApp();
        userApp.setId(1L);
        userApp.setFirstName("UserFirstName");
        userApp.setLastName("UserLastName");
        userApp.setEmail("user@com.pl");
        userApp.setLocalization("User workplace");
        userApp.setActive(true);
        userApp.setPosition("User work position");
    }

    @After
    public void cleanDatabase() {
        userRepository.deleteAll();
        userRepository.flush();
    }

    @Test
    public void shouldInsertUserToDatabase() {
        Collection<UserApp> userAppCollection = userRepository.findUsersByLastName("UserLastName");
        int collectionSize = userAppCollection.size();

        userRepository.save(userApp);
        Optional<UserApp> user1 = userRepository.findUserByUsernameAsEmail("user@com.pl");
        assertThat(user1.get().getId().longValue()).isNotEqualTo(0);

        userAppCollection = userRepository.findUsersByLastName("UserLastName");
        assertThat(userAppCollection.size()).isEqualTo(collectionSize + 1);
    }

    @Test
    public void shouldUpdateUserInformation(){
        UserApp userApp = userRepository.save(this.userApp);

        Optional<UserApp> userBeforeChange = userRepository.getUserById(1L);
        userBeforeChange.ifPresent(u -> u.setPassword("newPassword"));
        userBeforeChange.ifPresent(u -> userRepository.save(userApp));

        assertThat(userApp.getPassword()).isEqualTo("newPassword");
    }

    @Test
    public void shouldNotFindUserByLastName() {
        Collection<UserApp> userAppCollection =
                userRepository.findUsersByLastName("UserLastName");
        assertThat(userAppCollection.isEmpty()).isTrue();
    }

}