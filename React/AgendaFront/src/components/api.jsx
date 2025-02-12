import axios from "axios";

const API_URL = "http://localhost:8099/api/v1/Agenda";
const API_URL2 = "http://localhost:8098/api/v1/tutorials";

export const fetchAgenda = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error("Error al obtener datos de agenda:", error);
    return [];
  }
};

export const fetchTutorials = async () => {
  try {
    const response = await axios.get(API_URL2);
    return response.data;
  } catch (error) {
    console.error("Error al obtener datos de tutoriales:", error);
    return [];
  }
};
