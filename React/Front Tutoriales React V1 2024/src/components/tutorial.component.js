import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";

export default class Tutorial extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            title: "",
            description: "",
            published: false
        };
    }

    componentDidMount() {
        this.getTutorial(this.state.id);
    }

    getTutorial = async (id) => {
        try {
            const response = await TutorialDataService.get(id);
            const { title, description, published } = response.data;
            this.setState({ title, description, published });
        } catch (error) {
            console.error("Error al obtener el tutorial:", error);
        }
    };

    handleInputChange = (event) => {
        const { name, value, type, checked } = event.target;
        this.setState({
            [name]: type === "checkbox" ? checked : value
        });
    };

    editTutorial = async (event) => {
        event.preventDefault();

        const { id, title, description, published } = this.state;
        const data = {id, title, description, published };

        try {
            const response = await TutorialDataService.update(id, data);
            console.log("Tutorial actualizado:", response.data);
        } catch (error) {
            console.error("Error al actualizar el tutorial:", error);
        }
    };

    render() {
        return (
            <div>
                <h2>Edit Tutorial</h2>
                <form onSubmit={this.editTutorial}>
                    <div className="form-group">
                        <label htmlFor="id">ID</label>
                        <input
                            type="text"
                            className="form-control"
                            id="id"
                            name="id"
                            value={this.state.id}
                            disabled
                        />
                    </div>
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
                        <button type="submit" className="btn btn-success">
                            Update
                        </button>
                    </div>
                </form>
            </div>
        );
    }
}
