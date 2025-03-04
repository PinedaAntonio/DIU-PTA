import axios from "axios";

const API_URL = "http://localhost:8098/api/v1/tutorials";

const getAllTutorials = () => {
    return axios.get(API_URL);
};

export default {
    getAllTutorials,
};
