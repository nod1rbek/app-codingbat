package uz.pdp.app_codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_codingbat.payload.ArrayDto;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.service.ArrayService;

@RestController
@RequestMapping("/api/array")
public class ArrayController {
    @Autowired
    ArrayService arrayService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ArrayDto arrayDto) {
        ResponseApi responseApi = arrayService.addArray(arrayDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(arrayService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(arrayService.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody ArrayDto arrayDto) {
        ResponseApi edit = arrayService.edit(id, arrayDto);
        return ResponseEntity.status(edit.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ResponseApi delete = arrayService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(delete);
    }
}
