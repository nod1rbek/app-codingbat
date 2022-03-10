package uz.pdp.app_codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_codingbat.payload.LogicDto;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.service.LogicService;

@RestController
@RequestMapping("/api/logic")
public class LogicController {
    @Autowired
    LogicService logicService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody LogicDto logicDto) {
        ResponseApi responseApi = logicService.addLogic(logicDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(logicService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(logicService.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody LogicDto logicDto) {
        ResponseApi edit = logicService.edit(id, logicDto);
        return ResponseEntity.status(edit.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ResponseApi delete = logicService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(delete);
    }
}
