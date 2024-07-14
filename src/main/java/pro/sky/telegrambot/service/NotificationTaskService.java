package pro.sky.telegrambot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationTaskService {

    private final NotificationTaskRepository notificationTaskRepository;

    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    public void save(NotificationTask task) {
        notificationTaskRepository.save(task);
    }

    public void delete(NotificationTask task) {
        notificationTaskRepository.delete(task);
    }

    public List<NotificationTask> findAllByDateTime(LocalDateTime localDateTime) {
        return notificationTaskRepository.findAllByDateTime(localDateTime);
    }
}
