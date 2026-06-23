import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import api from '../api/axiosConfig'

export default function EspeciesPage() {
    const [especies, setEspecies] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')
    const [busqueda, setBusqueda] = useState('')
    const [mostrarFormulario, setMostrarFormulario] = useState(false)
    const [editando, setEditando] = useState(null)
    const [form, setForm] = useState({ nombre: '', descripcion: '' })
    const { isAdmin, isVeterinario, logout } = useAuth()
    const navigate = useNavigate()

    useEffect(() => { cargarEspecies() }, [])

    const cargarEspecies = async () => {
        try {
            const res = await api.get('/api/v1/especies')
            setEspecies(res.data)
        } catch {
            setError('Error al cargar especies')
        } finally {
            setLoading(false)
        }
    }

    const guardar = async () => {
        try {
            if (editando) {
                await api.put(`/api/v1/especies/${editando}`, form)
            } else {
                await api.post('/api/v1/especies', form)
            }
            setForm({ nombre: '', descripcion: '' })
            setEditando(null)
            setMostrarFormulario(false)
            cargarEspecies()
        } catch {
            alert('Error al guardar')
        }
    }

    const editar = (e) => {
        setEditando(e.id)
        setForm({ nombre: e.nombre, descripcion: e.descripcion })
        setMostrarFormulario(true)
    }

    const eliminar = async (id) => {
        if (!window.confirm('¿Eliminar esta especie?')) return
        try {
            await api.delete(`/api/v1/especies/${id}`)
            cargarEspecies()
        } catch {
            alert('Error al eliminar')
        }
    }

    const especiesFiltradas = especies.filter(e =>
        e.nombre?.toLowerCase().includes(busqueda.toLowerCase())
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
                    <button onClick={() => navigate('/mascotas')} style={btnNav}>Mascotas</button>
                    <button onClick={() => navigate('/consultas')} style={btnNav}>Consultas</button>
                    <button onClick={handleLogout} style={{ ...btnNav, backgroundColor: '#dc3545' }}>Cerrar sesión</button>
                </div>
            </nav>

            <div style={{ padding: '2rem' }}>
                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.5rem' }}>
                    <h2 style={{ color: '#2d6a4f', margin: 0 }}>🦎 Especies</h2>
                    {(isAdmin() || isVeterinario()) && (
                        <button onClick={() => { setMostrarFormulario(true); setEditando(null); setForm({ nombre: '', descripcion: '' }) }} style={btnPrimary}>
                            + Nueva especie
                        </button>
                    )}
                </div>

                {/* Formulario modal */}
                {mostrarFormulario && (
                    <div style={{
                        backgroundColor: 'white', borderRadius: '12px', padding: '1.5rem',
                        marginBottom: '1.5rem', boxShadow: '0 2px 8px rgba(0,0,0,0.1)',
                        border: '1px solid #dee2e6'
                    }}>
                        <h3 style={{ color: '#2d6a4f', marginTop: 0 }}>
                            {editando ? 'Editar especie' : 'Nueva especie'}
                        </h3>
                        <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
                            <div>
                                <label style={labelStyle}>Nombre</label>
                                <input value={form.nombre} onChange={e => setForm({ ...form, nombre: e.target.value })} style={inputStyle} />
                            </div>
                            <div>
                                <label style={labelStyle}>Descripción</label>
                                <input value={form.descripcion} onChange={e => setForm({ ...form, descripcion: e.target.value })} style={inputStyle} />
                            </div>
                        </div>
                        <div style={{ display: 'flex', gap: '1rem', marginTop: '1rem' }}>
                            <button onClick={guardar} style={btnPrimary}>Guardar</button>
                            <button onClick={() => { setMostrarFormulario(false); setEditando(null) }} style={btnCancel}>Cancelar</button>
                        </div>
                    </div>
                )}

                <input
                    placeholder="Buscar por nombre..."
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
                            <th style={th}>Descripción</th>
                            <th style={th}>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        {especiesFiltradas.map((e, i) => (
                            <tr key={e.id} style={{ backgroundColor: i % 2 === 0 ? 'white' : '#f8f9fa' }}>
                                <td style={td}>{e.id}</td>
                                <td style={td}>{e.nombre}</td>
                                <td style={td}>{e.descripcion}</td>
                                <td style={td}>
                                    <div style={{ display: 'flex', gap: '8px' }}>
                                        {(isAdmin() || isVeterinario()) && (
                                            <button onClick={() => editar(e)} style={btnEdit}>Editar</button>
                                        )}
                                        {isAdmin() && (
                                            <button onClick={() => eliminar(e.id)} style={btnDelete}>Eliminar</button>
                                        )}
                                    </div>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    {especiesFiltradas.length === 0 && !loading && (
                        <p style={{ textAlign: 'center', color: '#6c757d', marginTop: '2rem' }}>
                            No se encontraron especies
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
const btnCancel = { backgroundColor: '#6c757d', color: 'white', border: 'none', padding: '10px 20px', borderRadius: '8px', cursor: 'pointer', fontWeight: '500' }
const inputStyle = { width: '100%', padding: '10px 14px', borderRadius: '8px', border: '1px solid #dee2e6', marginBottom: '1rem', fontSize: '0.95rem', boxSizing: 'border-box' }
const labelStyle = { display: 'block', marginBottom: '4px', fontSize: '0.9rem', color: '#495057', fontWeight: '500' }
const tableStyle = { width: '100%', borderCollapse: 'collapse', backgroundColor: 'white', borderRadius: '8px', overflow: 'hidden', boxShadow: '0 2px 8px rgba(0,0,0,0.08)' }
const th = { padding: '12px 16px', textAlign: 'left', fontWeight: '500' }
const td = { padding: '12px 16px', borderBottom: '1px solid #f0f0f0' }