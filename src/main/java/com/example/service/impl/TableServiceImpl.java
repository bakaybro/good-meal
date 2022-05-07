package com.example.service.impl;

import com.example.converter.TableReservationConverter;
import com.example.entity.Institution;
import com.example.entity.TableReservation;
import com.example.exceptions.ApiException;
import com.example.model.TableReservationModel;
import com.example.repository.InstitutionRepository;
import com.example.repository.TableReservationRepository;
import com.example.service.TableReservationService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableServiceImpl implements TableReservationService {

    @Autowired
    private TableReservationConverter tableReservationConverter;

    @Autowired
    private TableReservationRepository tableReservationRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserService userService;

    @Override
    public TableReservationModel create(TableReservationModel tableReservationModel) {
        if (tableReservationModel.getInstitutionId() == null) throw new ApiException("Enter a institution name", HttpStatus.BAD_REQUEST);
        if (tableReservationModel.getTableNumber() == null) throw new ApiException("Enter the number of table", HttpStatus.BAD_REQUEST);
        if (tableReservationModel.getPlace() == null) throw new ApiException("Enter the number of seats", HttpStatus.BAD_REQUEST);
        Institution institution = institutionRepository.findInstitutionId(tableReservationModel.getInstitutionId()).orElse(null);
        if (!institution.getUser().getId().equals(userService.getCurrentUser().getId()))
            throw new ApiException("You cannot make changes to this institution", HttpStatus.BAD_REQUEST);;
        List<TableReservation> establishmentTables = institutionRepository.findInstitutionName(tableReservationModel.getInstitutionId());
        for (TableReservation establishmentTable:establishmentTables) {
            if (tableReservationModel.getTableNumber().equals(establishmentTable.getTableNumber()))
                throw new ApiException("Such a table with such a number already exists: " + tableReservationModel.getTableNumber(), HttpStatus.BAD_REQUEST);
        }
        tableReservationRepository.save(tableReservationConverter.convertFromModel(tableReservationModel));
        return tableReservationModel;
    }

    @Override
    public TableReservationModel getById(Long id) {
        return tableReservationConverter.convertFromEntity(tableReservationRepository.findById(id)
                .orElseThrow( () -> new ApiException("Didn't find a table under id: " + id, HttpStatus.BAD_REQUEST)));
    }

    @Override
    public List<TableReservationModel> getAll() {
        List<TableReservationModel> tableModels = new ArrayList<>();
        for (TableReservation establishmentTable : tableReservationRepository.findAll()) {
            tableModels.add(tableReservationConverter.convertFromEntity(establishmentTable));
        }
        if (tableModels.isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return tableModels;
    }

    @Override
    public TableReservationModel update(TableReservationModel tableReservationModel) {
        TableReservationModel tableModelForUpdate = getById(tableReservationConverter.convertFromModel(tableReservationModel).getId());

        if (tableReservationModel.getInstitutionId() != null) tableModelForUpdate.setInstitutionId(tableReservationModel.getInstitutionId());
        if (tableReservationModel.getTableNumber() != null) tableModelForUpdate.setTableNumber(tableReservationModel.getTableNumber());
        if (tableReservationModel.getPlace() != null) tableModelForUpdate.setPlace(tableReservationModel.getPlace());

        tableReservationRepository.save(tableReservationConverter.convertFromModel(tableModelForUpdate));
        return tableModelForUpdate;
    }

    @Override
    public TableReservationModel deleteById(Long id) {
        TableReservationModel reservationModelForDelete = tableReservationConverter.convertFromEntity(tableReservationRepository.findById(id)
                .orElseThrow( () -> new ApiException("Did not find the table under the id to delete. ID: " + id, HttpStatus.BAD_REQUEST)));

        tableReservationRepository.deleteById(id);
        return reservationModelForDelete;
    }
}
