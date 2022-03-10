package uz.pdp.app_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_codingbat.entity.Task;
import uz.pdp.app_codingbat.entity.List;
import uz.pdp.app_codingbat.payload.ListDto;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.repository.ListRepository;
import uz.pdp.app_codingbat.repository.TaskRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ListService {

    @Autowired
    ListRepository listRepository;
    @Autowired
    TaskRepository taskRepository;

    public ResponseApi addList(ListDto listDto) {
        Optional<Task> optionalTask = taskRepository.findById(listDto.getTaskId());
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            Set<Task> taskSet = new HashSet<>();
            taskSet.add(task);

            List list1 = new List();
            list1.setName(listDto.getName());
            list1.setTask(taskSet);

            listRepository.save(list1);
            return new ResponseApi("List added", true);
        }
        return new ResponseApi("ERROR!", false);
    }

   public java.util.List<List> getAll() {
       return listRepository.findAll();
   }

    public List getOne(Integer id) {
        Optional<List> optionalList = listRepository.findById(id);
        return optionalList.orElseGet(List::new);
    }

    public ResponseApi edit(Integer id, ListDto listDto) {
        Optional<List> optionalList = listRepository.findById(id);
        Optional<Task> optionalTask = taskRepository.findById(listDto.getTaskId());
        if (optionalTask.isPresent()) {
            if (optionalList.isPresent()) {
                Task task = optionalTask.get();
                Set<Task> taskSet = new HashSet<>();
                taskSet.add(task);

                List editingList = optionalList.get();
                editingList.setName(listDto.getName());
                editingList.setTask(taskSet);

                listRepository.save(editingList);
                return new ResponseApi("Edited", true);
            }
        }
        return new ResponseApi("ERROR!", false);
    }

    public ResponseApi delete(Integer id) {
        try {
            listRepository.deleteById(id);
            return new ResponseApi("Deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR!", false);
        }
    }
}
