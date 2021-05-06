class AuthenticationService {
    signin = (username, password) => {
        return fetch("/v1/auth/login",
            {
                method: 'POST',
                body: JSON.stringify({"username": username, "password": password})})
            .then(response => {
                if (response.data.accessToken) {
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

    register = async(firstname, lastname, username, email, password) => {
        return await fetch("/v1/auth/register", {
            firstname,
            lastname,
            username,
            email,
            password
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));;
    }
}

export default new AuthenticationService();