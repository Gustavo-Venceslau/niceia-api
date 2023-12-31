package com.galmv.chat.useCases.updateChat;

import com.galmv.chat.constants.ChatErrors;
import com.galmv.chat.entities.Chat;
import com.galmv.chat.entities.GroupChat;
import com.galmv.chat.exceptions.ChatNotFoundException;
import com.galmv.chat.models.GroupChatResponseModel;
import com.galmv.chat.models.GroupChatRequestModel;
import com.galmv.chat.ports.ChatRepository;
import com.galmv.chat.presenters.ChatPresenter;

import java.util.Optional;
import java.util.UUID;

public class UpdateChatUseCase implements UpdateChat{

    private final ChatRepository repository;
    private final ChatPresenter presenter;

    public UpdateChatUseCase(ChatRepository repository, ChatPresenter presenter){
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public GroupChatResponseModel updateGroupChat(UUID chatId, GroupChatRequestModel newData) {
        Optional<Chat> optionalChat = this.repository.findById(chatId);

        if(optionalChat.isEmpty()) throw new ChatNotFoundException(ChatErrors.CHAT_NOT_FOUND);

        GroupChat groupChatToUpdate = (GroupChat) optionalChat.get();

        updateGroupChatData(groupChatToUpdate, newData);

        return presenter.prepareGroupChatView(groupChatToUpdate);
    }

    private void updateGroupChatData(GroupChat groupChatToUpdate, GroupChatRequestModel newData) {
        groupChatToUpdate.setName(newData.name());
        groupChatToUpdate.setPhoto(newData.photo());
        groupChatToUpdate.setDescription(newData.description());
        groupChatToUpdate.setArchived(newData.archived());
    }
}
