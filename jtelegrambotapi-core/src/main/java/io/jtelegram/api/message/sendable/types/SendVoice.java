package io.jtelegram.api.message.sendable.types;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.chat.id.LongChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.VoiceMessage;
import io.jtelegram.api.message.sendable.InputFileMessageRequest;
import io.jtelegram.api.message.sendable.input.file.InputFile;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import io.jtelegram.api.message.sendable.SendableMessageRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@ToString
@Getter
public class SendVoice extends InputFileMessageRequest<VoiceMessage> {
    private final InputFile voice;
    private final String caption;
    private final Integer duration;

    @Builder
    protected SendVoice(Consumer<VoiceMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile voice, String caption, Integer duration) {
        super("sendVoice", VoiceMessage.class, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
        this.voice = voice;
        this.caption = caption;
        this.duration = duration;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && voice != null;
    }

    @Override
    public InputFile getInputFile() {
        return voice;
    }

    public static class SendVoiceBuilder {
        public SendVoiceBuilder chatId(Chat chat) {
            this.chatId = new LongChatId(chat.getId());
            return this;
        }

        public SendVoice.SendVoiceBuilder chatId(ChatId chatId) {
            this.chatId = chatId;
            return this;
        }
    }
}