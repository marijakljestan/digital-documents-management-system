import { searchByField, searchByFields, searchByPhrase, booleanSearch, geospatialSearch, searchByCVContent, searchByCoverLetterContent } from "../api/ElasticsearchApi";

export const ElasticsearchService = {

    searchByField: function(simpleQueryDto){
        return searchByField(simpleQueryDto);
    },

    searchByFields: function(firstQuery, secondQuery){
        const simpleQueryList = [];
        simpleQueryList.push(firstQuery);
        simpleQueryList.push(secondQuery);
        return searchByFields(simpleQueryList);
    },

    searchByCVContent: function(cvContent) {
        return searchByCVContent(cvContent);
    },

    searchByCoverLetterContent: function(coverLetterContent) {
        return searchByCoverLetterContent(coverLetterContent);
    },

    searchByPhrase: function(simpleQueryDto){
        return searchByPhrase(simpleQueryDto);
    },

    booleanSearch: function(firstQuery, secondQuery, booleanOperation) {
        const advancedSearchQuery = {
            "field1" : firstQuery.field,
            "value1" : firstQuery.value,
            "field2" : secondQuery.field,
            "value2" : secondQuery.value,
            "operation" : booleanOperation
        }
        return booleanSearch(advancedSearchQuery);
    },

    geospatialSearch: function(geospatialFormData) {
        return geospatialSearch(geospatialFormData);
    }
}