package com.galmv.user.useCases.updateUser;

import com.galmv.user.models.UserRequestModel;
import com.galmv.user.models.UserResponseModel;
import com.galmv.user.ports.PasswordEncoder;
import com.galmv.user.ports.UserRepository;
import com.galmv.user.presenters.UserPresenter;
import com.galmv.user.constants.UserErrors;
import com.galmv.user.entities.User;
import com.galmv.user.exceptions.UserNotFoundException;

import java.util.Optional;
import java.util.UUID;

public class UpdateUserUseCase implements UpdateUser{

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserPresenter presenter;

    public UpdateUserUseCase(UserRepository repository, PasswordEncoder encoder, UserPresenter presenter) {
        this.repository = repository;
        this.encoder = encoder;
        this.presenter = presenter;
    }

    @Override
    public UserResponseModel executeWith(UUID userId, UserRequestModel newData) {
        Optional<User> optionalUser = this.repository.findById(userId);

        if(optionalUser.isEmpty()) throw new UserNotFoundException(UserErrors.USER_NOT_FOUND_TO_UPDATE);

        User userToUpdate = optionalUser.get();

        updateUserDataWith(userToUpdate, newData);

        User updatedUser = this.repository.update(userToUpdate);

        return presenter.prepareSuccessView(updatedUser);
    }

    private void updateUserDataWith(User userToUpdate, UserRequestModel newData) {
        userToUpdate.setName(newData.name());
        userToUpdate.setEmail(newData.email());
        userToUpdate.setPassword(encoder.encode(newData.password()));
        userToUpdate.setPhone(newData.phone());
        userToUpdate.setStatus(newData.status());
        userToUpdate.setPhoto(newData.photo());
    }
}
