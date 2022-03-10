package uz.pdp.app_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_codingbat.entity.Array;
import uz.pdp.app_codingbat.entity.Task;
import uz.pdp.app_codingbat.payload.ArrayDto;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.repository.ArrayRepository;
import uz.pdp.app_codingbat.repository.TaskRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArrayService {

    @Autowired
    ArrayRepository arrayRepository;
    @Autowired
    TaskRepository taskRepository;

    public ResponseApi addArray(ArrayDto arrayDto) {
        Optional<Task> optionalTask = taskRepository.findById(arrayDto.getTaskId());
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            Set<Task> taskSet = new HashSet<>();
            taskSet.add(task);

            Array array = new Array();
            array.setName(arrayDto.getName());
            array.setTask(taskSet);

            arrayRepository.save(array);
            return new ResponseApi("Array added", true);
        }
        return new ResponseApi("ERROR!", false);
    }

   public List<Array> getAll() {
       return arrayRepository.findAll();
   }

    public Array getOne(Integer id) {
        Optional<Array> optionalArray = arrayRepository.findById(id);
        return optionalArray.orElseGet(Array::new);
    }

    public ResponseApi edit(Integer id, ArrayDto arrayDto) {
        Optional<Array> optionalArray = arrayRepository.findById(id);
        Optional<Task> optionalTask = taskRepository.findById(arrayDto.getTaskId());
        if (optionalTask.isPresent()) {
            if (optionalArray.isPresent()) {
                Task task = optionalTask.get();
                Set<Task> taskSet = new HashSet<>();
                taskSet.add(task);

                Array editingArray = optionalArray.get();
                editingArray.setName(arrayDto.getName());
                editingArray.setTask(taskSet);

                arrayRepository.save(editingArray);
                return new ResponseApi("Edited", true);
            }
        }
        return new ResponseApi("ERROR!", false);
    }

    public ResponseApi delete(Integer id) {
        try {
            arrayRepository.deleteById(id);
            return new ResponseApi("Deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR!", false);
        }
    }
}
