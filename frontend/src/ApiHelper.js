import dotenv from "dotenv"

import logger from "./Logger"

dotenv.config()

class ApiHelper {

    backend_url = process.env.REACT_APP_BACKEND_API_BASE_URL;

    async get(urlPath) {
        const response = await fetch(`${this.backend_url}${urlPath}`, {method: 'GET'});
        return response.json();
    }

    async get_students(urlPath) {
        let response;
        try {
            response = await fetch(`${this.backend_url}/v1${urlPath}`, {
                method: 'GET',
                headers: {'Origin': 'http://localhost:3000'}
            });
        } catch (error) {
            logger.error('Failed to get students in subjectStudent', error)
        }

        if (response.status === 404) {
            logger.error("No students enrolled")
            return [];
        }

        return response.json();
    }

    async delete(urlPath) {
        await fetch(`${this.backend_url}${urlPath}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    async edit_subject(urlPath, subject) {
        await fetch(`${this.backend_url}${urlPath}` + (subject.id ? '/' + subject.id : ''), {
            method: (subject.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(subject),
        });
    }
}

export default ApiHelper