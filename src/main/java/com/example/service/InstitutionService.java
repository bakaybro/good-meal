package com.example.service;

import com.example.exceptions.ApiException;
import com.example.model.InstitutionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InstitutionService {
    InstitutionModel create(InstitutionModel institutionModel);
    InstitutionModel getById(Long id);
    InstitutionModel update(InstitutionModel institutionModel);
    InstitutionModel deleteById(Long id);
    List<InstitutionModel> getAll();
    Page<InstitutionModel> getPage(Pageable pageable);
    Page<InstitutionModel> getPageSortedByCategory(Long id, Pageable pageable);
    Page<InstitutionModel> getSortedPage(InstitutionModel institutionModel, Pageable pageable);
    ApiException saveImages(List<MultipartFile> images, Long institutionId);
}
