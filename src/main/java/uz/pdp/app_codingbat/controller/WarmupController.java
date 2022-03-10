package uz.pdp.app_codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_codingbat.payload.ArrayDto;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.payload.WarmupDto;
import uz.pdp.app_codingbat.service.WarmupService;

@RestController
@RequestMapping("/api/array")
public class WarmupController {
    @Autowired
    WarmupService warmupService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody WarmupDto warmupDto) {
        ResponseApi responseApi = warmupService.addWarmup(warmupDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(warmupService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(warmupService.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody ArrayDto arrayDto) {
        ResponseApi edit = warmupService.edit(id, arrayDto);
        return ResponseEntity.status(edit.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ResponseApi delete = warmupService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(delete);
    }
}
