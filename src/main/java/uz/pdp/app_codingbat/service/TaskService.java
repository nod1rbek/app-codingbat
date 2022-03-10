package uz.pdp.app_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_codingbat.entity.Task;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    /**
     * Add task
     */
    public ResponseApi addTask(Task task) {
        boolean exists = taskRepository.existsByName(task.getName());
        if (exists)
            return new ResponseApi("This task already exist", false);
        Task addedTask = new Task(task.getName(), task.getQuestion(), task.getAnswer());
        taskRepository.save(addedTask);
        return new ResponseApi("Task added", true);
    }

    /**
     * Get all tasks
     */
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    /**
     * Get one task
     */
    public Task getOneTask(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElseGet(Task::new);
    }

    /**
     * Upload task
     */
    public ResponseApi editTask(Integer id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task editedTask = optionalTask.get();
            editedTask.setName(task.getName());
            editedTask.setQuestion(task.getQuestion());
            editedTask.setAnswer(task.getAnswer());

            taskRepository.save(editedTask);
            return new ResponseApi("Task edited", true);
        }
        return new ResponseApi("ERROR! Task not found", false);
    }

    /**
     * Delete task
     */
    public ResponseApi deleteTask(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new ResponseApi("Task deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR! Task not found", false);
        }
    }
}
