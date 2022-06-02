package com.campus02.todolist.model.tasks;

import com.campus02.todolist.model.tasks.dtos.SyncRequestDto;
import com.campus02.todolist.model.tasks.dtos.SyncResponseDto;
import com.campus02.todolist.model.tasks.dtos.TaskDto;
import com.campus02.todolist.model.tasks.repository.TaskFetchInfo;
import com.campus02.todolist.model.tasks.repository.TasksRepository;
import com.campus02.todolist.model.users.User;
import com.campus02.todolist.model.users.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;
    private final UsersRepository usersRepository;

    public TasksService(TasksRepository tasksRepository, UsersRepository usersRepository) {
        this.tasksRepository = tasksRepository;
        this.usersRepository = usersRepository;
    }

    public List<TaskFetchInfo> fetchTasksFromUser(Integer userId) {
        if (!userExists(userId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Kein Benutzer mit dieser ID vorhanden!");

        return this.tasksRepository.findByOriginatorUser_IdOrIsPublicIsTrue(userId);
    }

    public SyncResponseDto synchronizeTasksFromUser(SyncRequestDto syncRequest, Integer userId) {
        if (!userExists(userId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Kein Benutzer mit dieser ID vorhanden!");

        SyncResponseDto res = new SyncResponseDto();
        res.retrieved = handleGet(syncRequest.toRetrieve);
        res.persisted = handlePost(syncRequest.toPersist);
        res.deleted = handleDel(syncRequest.toDelete);
        return res;
    }
    private List<TaskDto> handleGet(List<UUID> list) {
        List<TaskDto> result = new ArrayList<>();
        if (list.isEmpty())
            return result;

        var tasks = this.tasksRepository.findAllById(list);
        result = StreamSupport.stream(tasks.spliterator(), false).map(TaskDto::from).collect(Collectors.toList());
        return result;
    }

    private List<UUID> handlePost(List<TaskDto> list) {
        List<UUID> result = new ArrayList<>();
        if (list.isEmpty())
            return result;

        var persisted = this.tasksRepository.saveAll(list.stream().map(TaskDto::to).toList());
        result = StreamSupport.stream(persisted.spliterator(), false).map(Task::getId).collect(Collectors.toList());
        return result;
    }
    private List<UUID> handleDel(List<UUID> list) {
        List<UUID> result = new ArrayList<>();
        if (list.isEmpty())
            return result;
        else {
            var tasks = this.tasksRepository.deleteByIdIn(list);
            result = tasks.stream().map(Task::getId).collect(Collectors.toList());
            return result;
        }
    }

    private boolean userExists(int userId) {
        var u = usersRepository.findById(userId);
        return u.isPresent();
    }

}
