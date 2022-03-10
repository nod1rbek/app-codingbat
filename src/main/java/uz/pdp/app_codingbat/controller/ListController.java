package uz.pdp.app_codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_codingbat.payload.ListDto;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.service.ListService;

@RestController
@RequestMapping("/api/list")
public class ListController {
    @Autowired
    ListService listService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ListDto listDto) {
        ResponseApi responseApi = listService.addList(listDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(listService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(listService.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody ListDto listDto) {
        ResponseApi edit = listService.edit(id, listDto);
        return ResponseEntity.status(edit.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ResponseApi delete = listService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(delete);
    }
}
