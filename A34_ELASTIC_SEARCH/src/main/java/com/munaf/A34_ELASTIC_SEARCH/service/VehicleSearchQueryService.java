package com.munaf.A34_ELASTIC_SEARCH.service;

import com.munaf.A34_ELASTIC_SEARCH.document.VehicleDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleSearchQueryService {


    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public List<VehicleDocument> getVehiclesByBrandAndModel(String brand, String model) {

        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q.bool(b -> b
                        .must(m -> m.match(mm -> mm.field("brand").query(brand)))
                        .must(m -> m.match(mm -> mm.field("model").query(model)))
                ))
                .build();

        SearchHits<VehicleDocument> searchHits =
                elasticsearchOperations.search(query, VehicleDocument.class);

        return searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }


    // this will return all documents
    public List<VehicleDocument> getVehiclesWithMatchAll() {

        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q.matchAll(m -> m))
                .withPageable(PageRequest.of(0, 10, Sort.by("price").ascending())) // optional
                .build();

        SearchHits<VehicleDocument> searchHits =
                elasticsearchOperations.search(query, VehicleDocument.class, IndexCoordinates.of("vehicles"));

        return searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }


}

// ElasticSearchClient