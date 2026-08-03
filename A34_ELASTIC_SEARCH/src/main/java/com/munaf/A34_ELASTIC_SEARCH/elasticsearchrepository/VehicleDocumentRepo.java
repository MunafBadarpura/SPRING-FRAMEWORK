package com.munaf.A34_ELASTIC_SEARCH.elasticsearchrepository;

import com.munaf.A34_ELASTIC_SEARCH.document.VehicleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface VehicleDocumentRepo extends ElasticsearchRepository<VehicleDocument, String> {

    List<VehicleDocument> findByBrand(String brand);

}
