import axios from 'axios';

axios.interceptors.request.use(config => {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user && user.accessToken) {
        config.headers.Authorization = 'Bearer ' + user.accessToken;
    }

    return config;
});

const backendServer = axios.create({
    baseURL: process.env.REACT_APP_BACKEND_API_BASE_URL,
});

class StudentService {
    backend_url = process.env.REACT_APP_BACKEND_API_BASE_URL;

    async getStudentCourses() {
        const user = JSON.parse(localStorage.getItem('user'));
        const response = await backendServer(`/v1/student/${user.id}/courses`);
        return response.data;
    }

    async getAllCourses() {
        const response = await axios.get(`${this.backend_url}/v1/subjects`);
        console.log(response.data);
        return response.data;
    }

    async enrollStudent(subjects) {
        const user = JSON.parse(localStorage.getItem('user'));
        return await axios.put(`${this.backend_url}/v1/student/${user.id}/enroll`, subjects);
    }
}

export default new StudentService();