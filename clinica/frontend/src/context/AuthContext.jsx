import { createContext, useContext, useState, useEffect } from "react";
import { login as loginApi } from "../api/authApi";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
    const [user, setUser]       = useState(null);  // { username, role }
    const [loading, setLoading] = useState(true);

    // Al recargar la página, restaura la sesión guardada
    useEffect(() => {
        const storedUser = localStorage.getItem("user");
        if (storedUser) {
            setUser(JSON.parse(storedUser));
        }
        setLoading(false);
    }, []);

    const login = async (username, password) => {
        const res = await loginApi(username, password);
        const { token, username: uname, role } = res.data;
        const userData = { username: uname, role };
        localStorage.setItem("token", token);
        localStorage.setItem("user", JSON.stringify(userData));
        setUser(userData);
        return userData;
    };

    const logout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        setUser(null);
    };

    // Helpers de rol
    const isAdmin        = () => user?.role === "ROLE_ADMIN";
    const isVeterinario  = () => user?.role === "ROLE_VETERINARIO";
    const isUser         = () => user?.role === "ROLE_USER";
    const canWrite       = () => isAdmin() || isVeterinario();
    const onlyAdmin      = () => isAdmin();

    return (
        <AuthContext.Provider value={{
            user, loading,
            login, logout,
            isAdmin, isVeterinario, isUser, canWrite, onlyAdmin
        }}>
            {children}
        </AuthContext.Provider>
    );
}

export const useAuth = () => useContext(AuthContext);