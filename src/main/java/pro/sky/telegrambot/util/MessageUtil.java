package pro.sky.telegrambot.util;

import pro.sky.telegrambot.exceptions.IncorrectCreateTaskCommandException;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MessageUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static NotificationTask parseCreateCommand(Long chatId, String command) throws IncorrectCreateTaskCommandException {
        String[] parts = command.split("\\s+", 4); // Разделяем команду на части с помощью пробелов

        if (parts.length < 4 || !parts[0].equals("/create")) {
            throw new IncorrectCreateTaskCommandException("Command format is incorrect. Expected format: /create <description> <date> <time>");
        }

        String description = parts[1]; // Описание задачи
        String datePart = parts[2]; // Дата
        String timePart = parts[3]; // Время

        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(datePart + " " + timePart, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IncorrectCreateTaskCommandException("Date and time format is incorrect. Expected format: yyyy-MM-dd HH:mm");
        }

        return new NotificationTask(chatId, description, dateTime);
    }
}
