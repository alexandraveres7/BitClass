import axios from "axios";

class AuthenticationService {
    backend_url = process.env.REACT_APP_BACKEND_API_BASE_URL;
    login = (username, password) => {
        return axios.post(`${this.backend_url}/v1/auth/login`, {username, password})
            .then(response => {
                if (response.data.accessToken) {
                    console.log(response.data);
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                return response.data;
            })
            .catch(err => {
                console.log(err);
                throw err;
            });
    }

    signOut() {
        localStorage.removeItem("user");
    }

    register = async(name, username, email, password) => {
        return await axios.post(`${this.backend_url}/v1/auth/register`, {
            name,
            username,
            email,
            password
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }
}

export default new AuthenticationService();