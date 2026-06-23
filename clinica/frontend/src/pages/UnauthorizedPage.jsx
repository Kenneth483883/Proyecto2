import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'

export default function UnauthorizedPage() {
    const navigate = useNavigate()
    const { logout } = useAuth()

    const handleGoBack = () => {
        navigate('/dashboard')
    }

    const handleLogout = () => {
        logout()
        navigate('/login')
    }

    return (
        <div style={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            height: '100vh',
            backgroundColor: '#f8f9fa'
        }}>
            <div style={{
                background: 'white',
                padding: '3rem',
                borderRadius: '12px',
                textAlign: 'center',
                boxShadow: '0 4px 20px rgba(0,0,0,0.1)',
                maxWidth: '400px'
            }}>
                <div style={{ fontSize: '4rem', marginBottom: '1rem' }}>🚫</div>
                <h1 style={{ color: '#dc3545', marginBottom: '0.5rem' }}>Acceso Denegado</h1>
                <p style={{ color: '#6c757d', marginBottom: '2rem' }}>
                    No tenés permisos para acceder a esta sección.
                    Solo los administradores pueden realizar esta acción.
                </p>
                <div style={{ display: 'flex', gap: '1rem', justifyContent: 'center' }}>
                    <button
                        onClick={handleGoBack}
                        style={{
                            padding: '10px 24px',
                            backgroundColor: '#2d6a4f',
                            color: 'white',
                            border: 'none',
                            borderRadius: '8px',
                            cursor: 'pointer',
                            fontWeight: '500'
                        }}
                    >
                        Volver al inicio
                    </button>
                    <button
                        onClick={handleLogout}
                        style={{
                            padding: '10px 24px',
                            backgroundColor: '#dc3545',
                            color: 'white',
                            border: 'none',
                            borderRadius: '8px',
                            cursor: 'pointer',
                            fontWeight: '500'
                        }}
                    >
                        Cerrar sesión
                    </button>
                </div>
            </div>
        </div>
    )
}