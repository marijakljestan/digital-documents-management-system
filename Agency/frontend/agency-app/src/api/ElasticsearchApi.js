import {axiosInstance} from "./AxiosInstance";

export async function searchByField(simpleQueryDto) {
    await axiosInstance.post('/search/field', simpleQueryDto);
}

export async function searchByFields(listOfSimpleQueryDto) {
    await axiosInstance.post('/search/fields', listOfSimpleQueryDto);
}

export async function searchByPhrase(simpleQueryDto) {
    await axiosInstance.post('/search/phrase', simpleQueryDto);
}

export async function booleanSearch(advancedQueryDto) {
    await axiosInstance.post('/search/boolean', advancedQueryDto);
}

export async function geospatialSearch(geospatialFormData) {
    await axiosInstance.post('/search/geospatial', geospatialFormData);
}