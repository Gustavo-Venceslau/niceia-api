module useCase.userUseCase {
    exports com.galmv.useCases.findByName;
    exports com.galmv.useCases.createUser;
    exports com.galmv.presenters;
    exports com.galmv.models;
    exports com.galmv.ports;

    requires galmv.domain.user;
}