import React, { useEffect, useState } from 'react';
import { Container, Row, Col, Spinner } from 'react-bootstrap';
import AgendaItem from './AgendaItem';
import { fetchAgenda } from './api';

const AgendaList = () => {
    const [agendaList, setAgendaList] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const getData = async () => {
            const data = await fetchAgenda();
            setAgendaList(data);
            setLoading(false);
        };
        getData();
    }, []);

    return (
        <Container>
            <h2 className="mt-4">Lista de Agenda</h2>
            {loading ? (
                <Spinner animation="border" />
            ) : (
                <Row>
                    {agendaList.map((agenda) => (
                        <Col key={agenda.id} md={4}>
                            <AgendaItem agenda={agenda} />
                        </Col>
                    ))}
                </Row>
            )}
        </Container>
    );
};

export default AgendaList;
