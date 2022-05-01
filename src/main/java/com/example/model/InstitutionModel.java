package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionModel {
    private String name;
    private String address;
    private String startedWorkFrom;
    private String endOfWorkIn;
    private Long userId;
    private Long categoryId;
}
