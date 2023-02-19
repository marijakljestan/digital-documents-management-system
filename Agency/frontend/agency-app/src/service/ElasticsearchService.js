import { searchByField, searchByFields, searchByPhrase, booleanSearch, geospatialSearch } from "../api/ElasticsearchApi";

export const ElasticsearchService = {

    searchByField: function(simpleQueryDto){
        return searchByField(simpleQueryDto);
    },

    searchByFields: function(simpleQueryDtos){
        return searchByFields(simpleQueryDtos);
    },
    searchByPhrase: function(simpleQueryDto){
        return searchByPhrase(simpleQueryDto);
    },

    booleanSearch: function(booleanQuery) {
        return booleanSearch(booleanQuery);
    },

    geospatialSearch: function(geospatialFormData) {
        return geospatialSearch(geospatialFormData);
    }
}