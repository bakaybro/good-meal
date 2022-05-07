package com.example.controller;

import com.example.model.TableReservationModel;
import com.example.service.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
public class TableController {

    @Autowired
    private TableReservationService tableReservationService;

    @PostMapping
    public TableReservationModel create(@RequestBody TableReservationModel tableReservationModel) {
        return tableReservationService.create(tableReservationModel);
    }

    @GetMapping("/{id}")
    public TableReservationModel getById(@PathVariable Long id) {
        return tableReservationService.getById(id);
    }

    @GetMapping
    public List<TableReservationModel> getAll() {
        return tableReservationService.getAll();
    }

    @PutMapping
    public TableReservationModel update(@RequestBody TableReservationModel tableReservationModel) {
        return tableReservationService.update(tableReservationModel);
    }

    @DeleteMapping("/{id}")
    public TableReservationModel deleteById(@PathVariable Long id) {
        return tableReservationService.deleteById(id);
    }
}
