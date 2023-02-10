import {axiosInstance} from "./AxiosInstance";

export async function registerCandidate(candidate) {
    await axiosInstance.post('/candidate/register', candidate);
}