import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";

export default class AddTutorial extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: "",
            title: "",
            description: "",
            published: false
        };
    }

    handleInputChange = (event) => {
        const { name, value, type, checked } = event.target;
        this.setState({
            [name]: type === "checkbox" ? checked : value
        });
    };

    addTutorial = async (event) => {
        event.preventDefault();

        const { title, description, published } = this.state;
        const data = { title, description, published };

        try {
            const response = await TutorialDataService.create(data);
            console.log("Tutorial creado:", response.data);

            this.setState({
                title: "",
                description: "",
                published: false
            });
        } catch (error) {
            console.error("Error al crear el tutorial:", error);
        }
    };

    render() {
        return (
            <div>
                <h2>Add Tutorial</h2>
                <form onSubmit={this.addTutorial}>
                    <div className="form-group">
                        <label htmlFor="title">Title</label>
                        <input
                            type="text"
                            className="form-control"
                            id="title"
                            name="title"
                            value={this.state.title}
                            onChange={this.handleInputChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Description</label>
                        <input
                            type="text"
                            className="form-control"
                            id="description"
                            name="description"
                            value={this.state.description}
                            onChange={this.handleInputChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <input
                            type="checkbox"
                            id="published"
                            name="published"
                            checked={this.state.published}
                            onChange={this.handleInputChange}
                        />
                        <label htmlFor="published">Published</label><br />
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-success" onClick={this.addTutorial}>
                            Submit
                        </button>
                    </div>
                </form>
            </div>
        );
    }
}
