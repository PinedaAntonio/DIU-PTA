import axios from "axios";

const API_URL = "https://AgendaPTA-env.eba-aehmaihv.us-east-1.elasticbeanstalk.com:8098/api/v1/tutorials";

const getAllTutorials = () => {
    return axios.get(API_URL);
};

export default {
    getAllTutorials,
};
