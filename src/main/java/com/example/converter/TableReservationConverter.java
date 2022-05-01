package com.example.converter;

import com.example.entity.Institution;
import com.example.entity.TableReservation;
import com.example.model.TableReservationModel;
import org.springframework.stereotype.Component;

@Component
public class TableReservationConverter extends BaseConverter<TableReservationModel, TableReservation> {

    public TableReservationConverter() {
        super(TableReservationConverter::convertToEntity, TableReservationConverter::convertToModel);
    }

    private static TableReservationModel convertToModel(TableReservation entityToConvert){
        if (entityToConvert == null) return null;
        return TableReservationModel.builder()
                .tableNumber(entityToConvert.getTableNumber())
                .place(entityToConvert.getPlace())
                .institutionId(entityToConvert.getInstitution().getId())
                .build();
    }

    private static TableReservation convertToEntity(TableReservationModel modelToConvert){
        if (modelToConvert == null) return null;

        TableReservation tableToReturn = new TableReservation();

        tableToReturn.setPlace(modelToConvert.getPlace());
        tableToReturn.setTableNumber(modelToConvert.getTableNumber());

        Institution institution = new Institution();
        institution.setId(modelToConvert.getInstitutionId());

        tableToReturn.setInstitution(institution);

        return tableToReturn;
    }
}
