import axios from "axios";

const API_URL = "http://localhost:8099/api/v1/Agenda";

const getAllContacts = () => {
    return axios.get(API_URL);
};

export default {
    getAllContacts,
};
