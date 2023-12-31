package com.galmv.chat.models;

import com.galmv.message.entities.Message;
import com.galmv.user.entities.User;

import java.util.List;

public record GroupChatRequestModel(List<User> participants, List<Message> messages, List<User> admins, String name, String description, String photo, boolean archived) {
}
