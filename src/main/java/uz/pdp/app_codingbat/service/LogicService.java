package uz.pdp.app_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_codingbat.entity.Logic;
import uz.pdp.app_codingbat.entity.Task;
import uz.pdp.app_codingbat.payload.LogicDto;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.repository.LogicRepository;
import uz.pdp.app_codingbat.repository.TaskRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LogicService {

    @Autowired
    LogicRepository logicRepository;
    @Autowired
    TaskRepository taskRepository;

    public ResponseApi addLogic(LogicDto logicDto) {
        Optional<Task> optionalTask = taskRepository.findById(logicDto.getTaskId());
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            Set<Task> taskSet = new HashSet<>();
            taskSet.add(task);

            Logic logic = new Logic();
            logic.setName(logicDto.getName());
            logic.setTask(taskSet);

            logicRepository.save(logic);
            return new ResponseApi("Logic added", true);
        }
        return new ResponseApi("ERROR!", false);
    }

   public List<Logic> getAll() {
       return logicRepository.findAll();
   }

    public Logic getOne(Integer id) {
        Optional<Logic> optionalLogic = logicRepository.findById(id);
        return optionalLogic.orElseGet(Logic::new);
    }

    public ResponseApi edit(Integer id, LogicDto logicDto) {
        Optional<Logic> optionalLogic = logicRepository.findById(id);
        Optional<Task> optionalTask = taskRepository.findById(logicDto.getTaskId());
        if (optionalTask.isPresent()) {
            if (optionalLogic.isPresent()) {
                Task task = optionalTask.get();
                Set<Task> taskSet = new HashSet<>();
                taskSet.add(task);

                Logic editingLogic = optionalLogic.get();
                editingLogic.setName(logicDto.getName());
                editingLogic.setTask(taskSet);

                logicRepository.save(editingLogic);
                return new ResponseApi("Edited", true);
            }
        }
        return new ResponseApi("ERROR!", false);
    }

    public ResponseApi delete(Integer id) {
        try {
            logicRepository.deleteById(id);
            return new ResponseApi("Deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR!", false);
        }
    }
}
