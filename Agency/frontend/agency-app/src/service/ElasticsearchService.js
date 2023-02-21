import { searchByField, searchByFields, searchByPhrase, booleanSearch, geospatialSearch, searchByCVContent, searchByCoverLetterContent } from "../api/ElasticsearchApi";

export const ElasticsearchService = {

    searchByField: async function(simpleQueryDto){
        return await searchByField(simpleQueryDto);
    },

    searchByFields: async function(firstQuery, secondQuery){
        const simpleQueryList = [];
        simpleQueryList.push(firstQuery);
        simpleQueryList.push(secondQuery);
        return await searchByFields(simpleQueryList);
    },

    searchByCVContent: async function(cvContent) {
        return await searchByCVContent(cvContent);
    },

    searchByCoverLetterContent: async function(coverLetterContent) {
        return await searchByCoverLetterContent(coverLetterContent);
    },

    searchByPhrase: async function(simpleQueryDto){
        return await searchByPhrase(simpleQueryDto);
    },

    booleanSearch: async function(firstQuery, secondQuery, booleanOperation) {
        const advancedSearchQuery = {
            "field1" : firstQuery.field,
            "value1" : firstQuery.value,
            "field2" : secondQuery.field,
            "value2" : secondQuery.value,
            "operation" : booleanOperation
        }
        return await booleanSearch(advancedSearchQuery);
    },

    geospatialSearch: async function(geospatialFormData) {
        return await geospatialSearch(geospatialFormData);
    }
}