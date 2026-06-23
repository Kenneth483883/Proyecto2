import api from "./axiosConfig";

export const login = (username, password) =>
    api.post("/auth/login", { username, password });