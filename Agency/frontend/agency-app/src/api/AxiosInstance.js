import axios from 'axios';

const axiosConfig = {
    baseURL: 'http://localhost:8080/',
    headers: {
        'Content-Type': 'application/json',
    }
}

export const axiosInstance = axios.create(axiosConfig);