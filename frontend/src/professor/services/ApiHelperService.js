import dotenv from "dotenv"

import logger from "../../Logger"

dotenv.config()

class ApiHelperService {

    backend_url = process.env.REACT_APP_BACKEND_API_BASE_URL;
    user = JSON.parse(localStorage.getItem('user'));

    async get(urlPath) {
        console.log(this.user.accessToken);
        const response = await fetch(`${this.backend_url}${urlPath}`, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + this.user.accessToken,
            }
        });
        return response.json();
    }

    async get_students(urlPath) {
        console.log(this.user.accessToken);
        let response;
        try {
            response = await fetch(`${this.backend_url}/v1${urlPath}`, {
                method: 'GET',
                headers: {
                    'Origin': 'http://localhost:3000',
                    'Authorization': 'Bearer ' + this.user.accessToken,
                }
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
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.user.accessToken,
            }
        });
    }

    async edit_subject(urlPath, subject) {
        await fetch(`${this.backend_url}${urlPath}` + (subject.id ? '/' + subject.id : ''), {
            method: (subject.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.user.accessToken,
            },
            body: JSON.stringify(subject),
        });
    }
}

export default ApiHelperService