package com.example.controller;

import com.example.model.ReservationModel;
import com.example.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ReservationModel create(@RequestBody ReservationModel reservationModel) {
        return reservationService.create(reservationModel);
    }

    @GetMapping("/{id}")
    public ReservationModel getById(@PathVariable Long id) {
        return reservationService.getById(id);
    }

    @GetMapping
    public List<ReservationModel> getAll() {
        return reservationService.getAll();
    }

    @PutMapping
    public ReservationModel update(@RequestBody ReservationModel reservationModel) {
        return reservationService.update(reservationModel);
    }

    @DeleteMapping("/{id}")
    public ReservationModel deleteById(@PathVariable Long id) {
        return reservationService.deleteById(id);
    }
}
