import {axiosInstance} from "./AxiosInstance";

export async function searchByField(simpleQueryDto) {
    const response = await axiosInstance.post('/search/field', simpleQueryDto)
    return response.data;
}

export async function searchByFields(listOfSimpleQueryDto) {
    const response = await axiosInstance.post('/search/fields', listOfSimpleQueryDto);
    return response.data;
}

export async function searchByCVContent(cvContent) {
    const response = await axiosInstance.post('/search/cv', cvContent);
    return response.data;
}

export async function searchByCoverLetterContent(coverLetterContent) {
    const response = await axiosInstance.post('/search/cover-letter', coverLetterContent);
    return response.data;
}

export async function searchByPhrase(simpleQueryDto) {
    const response = await axiosInstance.post('/search/phrase', simpleQueryDto);
    return response.data;
}

export async function booleanSearch(advancedQueryDto) {
    const response = await axiosInstance.post('/search/boolean', advancedQueryDto);
    return response.data;
}

export async function geospatialSearch(geospatialFormData) {
    const response = await axiosInstance.post('/search/geospatial', geospatialFormData);
    return response.data;
}