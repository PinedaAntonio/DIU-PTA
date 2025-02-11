import axios from 'axios';

const API_URL = 'http://localhost:8099/api/v1/Agenda';

export const fetchAgenda = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching agenda:', error);
        return [];
    }
};