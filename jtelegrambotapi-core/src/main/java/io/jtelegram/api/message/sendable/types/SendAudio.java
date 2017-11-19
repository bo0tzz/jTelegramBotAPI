package io.jtelegram.api.message.sendable.types;

import io.jtelegram.api.chat.Chat;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.impl.AudioMessage;
import io.jtelegram.api.message.sendable.InputFileMessageRequest;
import io.jtelegram.api.message.sendable.input.file.InputFile;
import io.jtelegram.api.message.sendable.ReplyMarkup;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.chat.id.LongChatId;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@ToString
@Getter
public class SendAudio extends InputFileMessageRequest<AudioMessage> {
    private final InputFile audio;
    private final String caption;
    private final Integer duration;
    private final String title;

    @Builder
    protected SendAudio(Consumer<AudioMessage> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageID, Boolean disableNotification, ReplyMarkup replyMarkup, InputFile audio, String caption, Integer duration, String title) {
        super("sendAudio", AudioMessage.class, callback, errorHandler, chatId, replyToMessageID, disableNotification, replyMarkup);
        this.audio = audio;
        this.caption = caption;
        this.duration = duration;
        this.title = title;
    }

    @Override
    protected boolean isValid() {
        return super.isValid() && audio != null;
    }

    @Override
    public InputFile getInputFile() {
        return audio;
    }

    public static class SendAudioBuilder {
        public SendAudio.SendAudioBuilder chatId(Chat chat) {
            this.chatId = new LongChatId(chat.getId());
            return this;
        }

        public SendAudio.SendAudioBuilder chatId(ChatId chatId) {
            this.chatId = chatId;
            return this;
        }
    }
}