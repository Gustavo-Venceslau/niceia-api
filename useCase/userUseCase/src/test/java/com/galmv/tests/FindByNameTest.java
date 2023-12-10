package com.galmv.tests;

import com.galmv.config.UnitTestConfig;
import com.galmv.presenters.UserPresenter;
import com.galmv.presenters.UserPresenterViewer;
import com.galmv.useCases.findByName.FindByName;
import com.galmv.useCases.findByName.FindByNameUseCase;
import com.galmv.models.UserRequestModel;
import com.galmv.models.UserResponseModel;
import com.galmv.user.constants.Errors;
import com.galmv.user.entities.User;
import com.galmv.user.exceptions.custom.UserNotFoundException;
import com.galmv.user.factories.CommonUserFactory;
import com.galmv.user.factories.UserFactory;
import com.galmv.utils.InMemoryRepository;
import com.galmv.ports.UserRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FindByNameTest extends UnitTestConfig {

    private final FindByName findByName;

    public FindByNameTest(){
        this.findByName = new FindByNameUseCase(repository, presenter);
    }

    @Test
    public void givenAnUser_whenFoundByName_thenUserMustBeNotNull(){
        UserRequestModel request = new UserRequestModel(
                "John Doe",
                "john@mail.com",
                "123",
                "12345678910",
                "happy",
                "url"
        );

        UserResponseModel response = this.findByName.find(request.name());

        assertThat(response.name()).isEqualTo(user.getName());
        assertThat(response.email()).isEqualTo(user.getEmail());
        assertThat(response.password()).isEqualTo(user.getPassword());
        assertThat(response.phone()).isEqualTo(user.getPhone());
        assertThat(response.status()).isEqualTo(user.getStatus());
        assertThat(response.photo()).isEqualTo(user.getPhoto());
    }

    @Test
    public void givenAInvalidUsername_whenFoundByName_thenThrowAnException(){
        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(() ->
                this.findByName.find("invalid")).withMessage(Errors.USER_NOT_FOUND);
    }
}