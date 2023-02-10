import {registerCandidate} from '../api/CandidateApi';

export const RegisterCandidateService = {

    registerCandidate: function(candidate) {
        return registerCandidate(candidate);
    }
}