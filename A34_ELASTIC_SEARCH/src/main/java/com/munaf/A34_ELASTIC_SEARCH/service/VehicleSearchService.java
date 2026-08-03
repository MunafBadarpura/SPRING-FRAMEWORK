package com.munaf.A34_ELASTIC_SEARCH.service;

import com.munaf.A34_ELASTIC_SEARCH.document.VehicleDocument;
import com.munaf.A34_ELASTIC_SEARCH.elasticsearchrepository.VehicleDocumentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleSearchService {

    private final VehicleDocumentRepo vehicleDocumentRepo;

    public void saveVehicle(VehicleDocument doc) {
        vehicleDocumentRepo.save(doc);
    }

    public List<VehicleDocument> searchByBrand(String brand) {
        return vehicleDocumentRepo.findByBrand(brand);
    }

    public Iterable<VehicleDocument> saveAllVehicles(List<VehicleDocument> docs) {
        Iterable<VehicleDocument> vehicleDocuments = vehicleDocumentRepo.saveAll(docs);


        System.out.println(vehicleDocuments);

        return vehicleDocuments;
    }

    public Page<VehicleDocument> getAll() {
        Pageable pageable = PageRequest.of(0, 10);

        return vehicleDocumentRepo.findAll(pageable);
    }
}