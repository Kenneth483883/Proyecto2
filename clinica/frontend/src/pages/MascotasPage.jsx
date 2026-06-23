import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import api from '../api/axiosConfig'

export default function MascotasPage() {
    const [mascotas, setMascotas] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')
    const [busqueda, setBusqueda] = useState('')
    const { isAdmin, isVeterinario, logout } = useAuth()
    const navigate = useNavigate()

    useEffect(() => {
        cargarMascotas()
    }, [])

    const cargarMascotas = async () => {
        try {
            const res = await api.get('/api/v1/mascotas')
            setMascotas(res.data)
        } catch {
            setError('Error al cargar mascotas')
        } finally {
            setLoading(false)
        }
    }

    const eliminar = async (id) => {
        if (!window.confirm('¿Estás seguro de eliminar esta mascota?')) return
        try {
            await api.delete(`/api/v1/mascotas/${id}`)
            cargarMascotas()
        } catch {
            alert('Error al eliminar')
        }
    }

    const mascotasFiltradas = mascotas.filter(m =>
        m.nombre?.toLowerCase().includes(busqueda.toLowerCase()) ||
        m.especie?.nombre?.toLowerCase().includes(busqueda.toLowerCase())
    )

    const handleLogout = () => { logout(); navigate('/login') }

    return (
        <div style={{ minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
            <nav style={{
                backgroundColor: '#2d6a4f', padding: '0 2rem',
                display: 'flex', alignItems: 'center',
                justifyContent: 'space-between', height: '60px',
                boxShadow: '0 2px 8px rgba(0,0,0,0.2)'
            }}>
                <div style={{ display: 'flex', alignItems: 'center', gap: '8px', cursor: 'pointer' }}
                     onClick={() => navigate('/dashboard')}>
                    <span style={{ fontSize: '1.5rem' }}>🐾</span>
                    <span style={{ color: 'white', fontWeight: '600', fontSize: '1.1rem' }}>Clínica Veterinaria</span>
                </div>
                <div style={{ display: 'flex', gap: '1rem' }}>
                    <button onClick={() => navigate('/dashboard')} style={btnNav}>Dashboard</button>
                    <button onClick={() => navigate('/propietarios')} style={btnNav}>Propietarios</button>
                    <button onClick={() => navigate('/veterinarios')} style={btnNav}>Veterinarios</button>
                    <button onClick={() => navigate('/consultas')} style={btnNav}>Consultas</button>
                    <button onClick={handleLogout} style={{ ...btnNav, backgroundColor: '#dc3545' }}>Cerrar sesión</button>
                </div>
            </nav>

            <div style={{ padding: '2rem' }}>
                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.5rem' }}>
                    <h2 style={{ color: '#2d6a4f', margin: 0 }}>🐶 Mascotas</h2>
                    {(isAdmin() || isVeterinario()) && (
                        <button onClick={() => navigate('/mascotas/nueva')} style={btnPrimary}>
                            + Nueva mascota
                        </button>
                    )}
                </div>

                <input
                    placeholder="Buscar por nombre o especie..."
                    value={busqueda}
                    onChange={e => setBusqueda(e.target.value)}
                    style={inputStyle}
                />

                {loading && <p>Cargando...</p>}
                {error && <p style={{ color: 'red' }}>{error}</p>}

                <div style={{ overflowX: 'auto' }}>
                    <table style={tableStyle}>
                        <thead>
                        <tr style={{ backgroundColor: '#2d6a4f', color: 'white' }}>
                            <th style={th}>ID</th>
                            <th style={th}>Nombre</th>
                            <th style={th}>Especie</th>
                            <th style={th}>Raza</th>
                            <th style={th}>Propietario</th>
                            <th style={th}>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        {mascotasFiltradas.map((m, i) => (
                            <tr key={m.id} style={{ backgroundColor: i % 2 === 0 ? 'white' : '#f8f9fa' }}>
                                <td style={td}>{m.id}</td>
                                <td style={td}>{m.nombre}</td>
                                <td style={td}>{m.especie?.nombre}</td>
                                <td style={td}>{m.raza?.nombre}</td>
                                <td style={td}>{m.propietario?.nombre}</td>
                                <td style={td}>
                                    <div style={{ display: 'flex', gap: '8px' }}>
                                        {(isAdmin() || isVeterinario()) && (
                                            <button onClick={() => navigate(`/mascotas/${m.id}/editar`)} style={btnEdit}>
                                                Editar
                                            </button>
                                        )}
                                        {isAdmin() && (
                                            <button onClick={() => eliminar(m.id)} style={btnDelete}>
                                                Eliminar
                                            </button>
                                        )}
                                    </div>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    {mascotasFiltradas.length === 0 && !loading && (
                        <p style={{ textAlign: 'center', color: '#6c757d', marginTop: '2rem' }}>
                            No se encontraron mascotas
                        </p>
                    )}
                </div>
            </div>
        </div>
    )
}

const btnNav = { background: 'transparent', color: 'white', border: '1px solid rgba(255,255,255,0.3)', padding: '6px 14px', borderRadius: '6px', cursor: 'pointer', fontSize: '0.9rem' }
const btnPrimary = { backgroundColor: '#2d6a4f', color: 'white', border: 'none', padding: '10px 20px', borderRadius: '8px', cursor: 'pointer', fontWeight: '500' }
const btnEdit = { backgroundColor: '#f0ad4e', color: 'white', border: 'none', padding: '6px 12px', borderRadius: '6px', cursor: 'pointer', fontSize: '0.85rem' }
const btnDelete = { backgroundColor: '#dc3545', color: 'white', border: 'none', padding: '6px 12px', borderRadius: '6px', cursor: 'pointer', fontSize: '0.85rem' }
const inputStyle = { width: '100%', padding: '10px 14px', borderRadius: '8px', border: '1px solid #dee2e6', marginBottom: '1rem', fontSize: '0.95rem', boxSizing: 'border-box' }
const tableStyle = { width: '100%', borderCollapse: 'collapse', backgroundColor: 'white', borderRadius: '8px', overflow: 'hidden', boxShadow: '0 2px 8px rgba(0,0,0,0.08)' }
const th = { padding: '12px 16px', textAlign: 'left', fontWeight: '500' }
const td = { padding: '12px 16px', borderBottom: '1px solid #f0f0f0' }