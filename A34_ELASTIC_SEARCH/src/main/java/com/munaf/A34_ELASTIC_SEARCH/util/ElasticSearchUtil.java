package com.munaf.A34_ELASTIC_SEARCH.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class ElasticSearchUtil {


    public static Supplier<Query> supplier() {
        Supplier<Query> supplier = () -> {
            return Query.of(q -> q.matchAll(matchAllQuery()));
        };
        return supplier;
    }

    public static MatchAllQuery matchAllQuery() {

        MatchAllQuery matchAllQuery = new MatchAllQuery.Builder().build();
        return  matchAllQuery;
    }


}
