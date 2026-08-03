package com.munaf.A34_ELASTIC_SEARCH.controller;

import com.munaf.A34_ELASTIC_SEARCH.document.VehicleDocument;
import com.munaf.A34_ELASTIC_SEARCH.service.VehicleSearchQueryService;
import com.munaf.A34_ELASTIC_SEARCH.service.VehicleSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class VehicleSearchController {

    private final VehicleSearchService service;
    private final VehicleSearchQueryService queryService;

    @PostMapping
    public void save(@RequestBody VehicleDocument doc) {
        service.saveVehicle(doc);
    }

    @PostMapping("/save-all")
    public Iterable<VehicleDocument>  save(@RequestBody List<VehicleDocument> docs) {
        return service.saveAllVehicles(docs);
    }

    @GetMapping("/{brand}")
    public List<VehicleDocument> searchByBrand(@PathVariable String brand) {
        return service.searchByBrand(brand);
    }

    @GetMapping("/get-all")
    public Page<VehicleDocument> getAll() {
        return service.getAll();
    }


    @GetMapping("/get-by-brand-model/{brand}/{model}")
    public List<VehicleDocument> getAllByBrandAndModel(@PathVariable String brand, @PathVariable String model) {
        return queryService.getVehiclesByBrandAndModel(brand, model);
    }
}
