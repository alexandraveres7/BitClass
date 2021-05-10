import axios from 'axios';

axios.interceptors.request.use(config => {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user && user.accessToken) {
        config.headers.Authorization = 'Bearer ' + user.accessToken;
    }

    return config;
});

class BackendService {
    backend_url = process.env.REACT_APP_BACKEND_API_BASE_URL;

    async getStudentCourses() {
        const user = JSON.parse(localStorage.getItem('user'));
        const url = `${this.backend_url}/v1/student/${user.id}/courses`;
        console.log(url);
        const response = await axios.get(`${this.backend_url}/v1/student/${user.id}/courses`);
        return response.data;
    }

    async getProfessorBoard() {
        return await axios.get(`${this.backend_url}/v1/test/professor`);
    }
}

export default new BackendService();