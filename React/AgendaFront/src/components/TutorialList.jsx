import React, { useEffect, useState } from "react";
import { Carousel, Card } from "react-bootstrap";
import tutorialService from "../services/http-tutorials";
import "../App.css";

const TutorialList = ({ selectedContact }) => {
  const [tutorials, setTutorials] = useState([]);
  const [activeIndex, setActiveIndex] = useState(0);

  useEffect(() => {
    if (selectedContact && selectedContact.tutorials) {
      tutorialService
        .getAllTutorials()
        .then((response) => {
          const filteredTutorials = response.data.filter((tutorial) =>
            selectedContact.tutorials.includes(tutorial.id)
          );
          setTutorials(filteredTutorials);
          setActiveIndex(0);
        })
        .catch((error) =>
          console.error("Error al cargar los tutoriales:", error)
        );
    } else {
      setTutorials([]);
      setActiveIndex(0);
    }
  }, [selectedContact]);

  return (
    <Carousel
      className="tutorial-carousel"
      activeIndex={activeIndex}
      onSelect={(selectedIndex) => setActiveIndex(selectedIndex)}
    >
      {tutorials.length > 0 ? (
        tutorials.map((tutorial) => (
          <Carousel.Item key={tutorial.id}>
            <Card className="tutorial-card">
              <Card.Body>
                <Card.Title>{tutorial.title}</Card.Title>
                <Card.Text>{tutorial.description}</Card.Text>
              </Card.Body>
              <Card.Img
                variant="bottom"
                src={tutorial.imageUrl}
                alt={tutorial.title}
              />
            </Card>
          </Carousel.Item>
        ))
      ) : (
        <Carousel.Item>
          <Card className="tutorial-card">
            <Card.Body>
              <Card.Text>No hay tutoriales disponibles.</Card.Text>
            </Card.Body>
          </Card>
        </Carousel.Item>
      )}
    </Carousel>
  );
};

export default TutorialList;
