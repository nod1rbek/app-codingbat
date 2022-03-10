package uz.pdp.app_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_codingbat.entity.Task;
import uz.pdp.app_codingbat.entity.Warmup;
import uz.pdp.app_codingbat.payload.ArrayDto;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.payload.WarmupDto;
import uz.pdp.app_codingbat.repository.TaskRepository;
import uz.pdp.app_codingbat.repository.WarmupRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WarmupService {

    @Autowired
    WarmupRepository warmupRepository;
    @Autowired
    TaskRepository taskRepository;

    public ResponseApi addWarmup(WarmupDto warmupDto) {
        Optional<Task> optionalTask = taskRepository.findById(warmupDto.getTaskId());
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            Set<Task> taskSet = new HashSet<>();
            taskSet.add(task);

            Warmup warmup = new Warmup();
            warmup.setName(warmupDto.getName());
            warmup.setTask(taskSet);

            warmupRepository.save(warmup);
            return new ResponseApi("Warmup added", true);
        }
        return new ResponseApi("ERROR!", false);
    }

   public List<Warmup> getAll() {
       return warmupRepository.findAll();
   }

    public Warmup getOne(Integer id) {
        Optional<Warmup> optionalWarmup = warmupRepository.findById(id);
        return optionalWarmup.orElseGet(Warmup::new);
    }

    public ResponseApi edit(Integer id, ArrayDto arrayDto) {
        Optional<Warmup> optionalWarmup = warmupRepository.findById(id);
        Optional<Task> optionalTask = taskRepository.findById(arrayDto.getTaskId());
        if (optionalTask.isPresent()) {
            if (optionalWarmup.isPresent()) {
                Task task = optionalTask.get();
                Set<Task> taskSet = new HashSet<>();
                taskSet.add(task);

                Warmup editingWarmup = optionalWarmup.get();
                editingWarmup.setName(arrayDto.getName());
                editingWarmup.setTask(taskSet);

                warmupRepository.save(editingWarmup);
                return new ResponseApi("Edited", true);
            }
        }
        return new ResponseApi("ERROR!", false);
    }

    public ResponseApi delete(Integer id) {
        try {
            warmupRepository.deleteById(id);
            return new ResponseApi("Deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR!", false);
        }
    }
}
