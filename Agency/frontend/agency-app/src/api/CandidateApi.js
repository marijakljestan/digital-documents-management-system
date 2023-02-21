import {axiosInstance} from "./AxiosInstance";

export async function registerCandidate(candidate) {
    await axiosInstance.post('/candidate/register', candidate, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}