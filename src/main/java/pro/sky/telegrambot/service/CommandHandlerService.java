package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.configuration.TelegramBotConfiguration;
import pro.sky.telegrambot.exceptions.IncorrectCreateTaskCommandException;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.util.MessageUtil;

@Service
public class CommandHandlerService {

    private static NotificationTaskService notificationTaskService;
    private static TelegramBotConfiguration telegramBotConfiguration;

    private static final String START_COMMAND = "/start";
    private static final String HELP_COMMAND = "/help";
    public static String handleCommand(Long chatId, String command) {
        switch (command) {
            case START_COMMAND:
                return telegramBotConfiguration.getStartMsg();
            case HELP_COMMAND:
                return telegramBotConfiguration.getHelpMsg();
            default:
                return handleCreateTaskCommand(chatId, command);
        }
    }

    private static String handleCreateTaskCommand(Long chatId, String command) {
        try {
            NotificationTask notificationTask = MessageUtil.parseCreateCommand(chatId, command);
            notificationTaskService.save(notificationTask);
            return telegramBotConfiguration.getSuccessMsg();
        } catch (IncorrectCreateTaskCommandException e) {
            return telegramBotConfiguration.getErrorMsg();
        }
    }
}
