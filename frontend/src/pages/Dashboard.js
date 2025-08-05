import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Dashboard() {
  const [brands, setBrands] = useState([]);
  const [newBrand, setNewBrand] = useState('');
  const navigate = useNavigate();

  const fetchBrands = async () => {
    const res = await axios.get('https://my-majestic-grid-backend.onrender.com/api/brands');
    setBrands(res.data);
  };

  useEffect(() => {
    fetchBrands();
  }, []);

  const handleAdd = async () => {
    if (!newBrand.trim()) return alert('⚠️ Brand name required');

    const brandNameRegex = /^[a-zA-Z0-9\s]{2,30}$/;
    if (!brandNameRegex.test(newBrand)) {
      return alert('❌ Invalid brand name. Use only letters, numbers and space (2-30 chars)');
    }

    try {
      await axios.post('https://my-majestic-grid-backend.onrender.com/api/brands', { name: newBrand });
      setNewBrand('');
      fetchBrands();
    } catch (err) {
      const message = err.response?.data?.message;
      alert(message || '❌ Failed to add brand');
    }
  };

  const handleDelete = async (id) => {
    await axios.delete(`https://my-majestic-grid-backend.onrender.com/api/brands/${id}`);
    fetchBrands();
  };

  const handleUpdate = async (id, currentName) => {
    const updated = prompt('Update brand name:', currentName);
    if (updated) {
      await axios.put(`https://my-majestic-grid-backend.onrender.com/api/brands/${id}`, { name: updated });
      fetchBrands();
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    alert('Logout successful');
    navigate('/');
  };

  return (
    <div style={{ padding: '2rem' }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <h2>Global Majestic Car Brands</h2>
        <button onClick={handleLogout} style={{ padding: '0.5rem 1rem', background: '#f44336', color: '#fff', border: 'none', borderRadius: '5px' }}>
          Logout
        </button>
      </div>

      <input
        placeholder="New brand name"
        value={newBrand}
        onChange={e => setNewBrand(e.target.value)}
        style={{ marginRight: '1rem', marginTop: '1rem' }}
      />
      <button onClick={handleAdd}>Add Brand</button>

      {brands.length >= 5 && <p style={{ color: 'red', marginTop: '1rem' }}>🚫 Brand limit reached (5)</p>}

      <ul style={{ marginTop: '2rem' }}>
        {brands.map(brand => (
          <li key={brand._id}>
            {brand.name}
            <button onClick={() => handleUpdate(brand._id, brand.name)} style={{ marginLeft: '1rem' }}>Edit</button>
            <button onClick={() => handleDelete(brand._id)} style={{ marginLeft: '0.5rem' }}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Dashboard;
