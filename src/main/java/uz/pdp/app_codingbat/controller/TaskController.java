package uz.pdp.app_codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_codingbat.entity.Task;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Task task) {
        ResponseApi responseApi = taskService.addTask(task);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.getOneTask(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody Task task) {
        ResponseApi responseApi = taskService.editTask(id, task);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(responseApi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ResponseApi responseApi = taskService.deleteTask(id);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(responseApi);
    }
}
