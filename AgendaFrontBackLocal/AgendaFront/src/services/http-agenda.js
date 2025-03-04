import axios from 'axios';

const API_URL = "http://localhost:8099/api/v1/Agenda";

export const getAllContacts = () => {
    return axios.get(API_URL);
};

export const addContact = (contact) => {
    return axios.post(API_URL, contact);
};

export const editContact = (contactId, updatedContact) => {
    return axios.put(`${API_URL}/${contactId}`, updatedContact);
};

export const deleteContact = (contactId) => {
    return axios.delete(`${API_URL}/${contactId}`);
};

export default {
    getAllContacts,
    addContact,
    editContact,
    deleteContact
};
