import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './style.css';
import config from './config.js';

const PetManager = () => {
  const [pets, setPets] = useState([]);
  const [pet, setPet] = useState({
    id: '',
    name: '',
    species: '',
    breed: '',
    age: '',
    gender: '',
    ownerName: '',
    ownerContact: '',
    ownerEmail: ''
  });
  const [idToFetch, setIdToFetch] = useState('');
  const [fetchedPet, setFetchedPet] = useState(null);
  const [message, setMessage] = useState('');
  const [editMode, setEditMode] = useState(false);

  const baseUrl = `${config.url}`;

  useEffect(() => {
    fetchAllPets();
  }, []);

  const fetchAllPets = async () => {
    try {
      const res = await axios.get(`${baseUrl}/all`);
      setPets(res.data);
    } catch (error) {
      setMessage('Failed to fetch pets.');
    }
  };

  const handleChange = (e) => {
    setPet({ ...pet, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    for (let key in pet) {
      if (!pet[key] || pet[key].toString().trim() === '') {
        setMessage(`Please fill out the ${key} field.`);
        return false;
      }
    }
    return true;
  };

  const addPet = async () => {
    if (!validateForm()) return;
    try {
      await axios.post(`${baseUrl}/add`, pet);
      setMessage('Pet added successfully.');
      fetchAllPets();
      resetForm();
    } catch (error) {
      setMessage('Error adding pet.');
    }
  };

  const updatePet = async () => {
    if (!validateForm()) return;
    try {
      await axios.put(`${baseUrl}/update`, pet);
      setMessage('Pet updated successfully.');
      fetchAllPets();
      resetForm();
    } catch (error) {
      setMessage('Error updating pet.');
    }
  };

  const deletePet = async (id) => {
    try {
      const res = await axios.delete(`${baseUrl}/delete/${id}`);
      setMessage(res.data);
      fetchAllPets();
    } catch (error) {
      setMessage('Error deleting pet.');
    }
  };

  const getPetById = async () => {
    try {
      const res = await axios.get(`${baseUrl}/get/${idToFetch}`);
      setFetchedPet(res.data);
      setMessage('');
    } catch (error) {
      setFetchedPet(null);
      setMessage('Pet not found.');
    }
  };

  const handleEdit = (p) => {
    setPet(p);
    setEditMode(true);
    setMessage(`Editing pet with ID ${p.id}`);
  };

  const resetForm = () => {
    setPet({
      id: '',
      name: '',
      species: '',
      breed: '',
      age: '',
      gender: '',
      ownerName: '',
      ownerContact: '',
      ownerEmail: ''
    });
    setEditMode(false);
  };

  return (
    <div className="pet-container">

      {message && (
        <div className={`message-banner ${message.toLowerCase().includes('error') ? 'error' : 'success'}`}>
          {message}
        </div>
      )}

      <h2>🐾 Pet Health Journal</h2>

      <div>
        <h3>{editMode ? 'Edit Pet' : 'Add Pet'}</h3>
        <div className="form-grid">
          <input type="number" name="id" placeholder="ID" value={pet.id} onChange={handleChange} />
          <input type="text" name="name" placeholder="Pet Name" value={pet.name} onChange={handleChange} />
          <input type="text" name="species" placeholder="Species (Dog, Cat...)" value={pet.species} onChange={handleChange} />
          <input type="text" name="breed" placeholder="Breed" value={pet.breed} onChange={handleChange} />
          <input type="number" name="age" placeholder="Age" value={pet.age} onChange={handleChange} />
          <select name="gender" value={pet.gender} onChange={handleChange}>
            <option value="">Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
          </select>
          <input type="text" name="ownerName" placeholder="Owner Name" value={pet.ownerName} onChange={handleChange} />
          <input type="text" name="ownerContact" placeholder="Owner Contact" value={pet.ownerContact} onChange={handleChange} />
          <input type="email" name="ownerEmail" placeholder="Owner Email" value={pet.ownerEmail} onChange={handleChange} />
        </div>

        <div className="btn-group">
          {!editMode ? (
            <button className="btn-blue" onClick={addPet}>Add Pet</button>
          ) : (
            <>
              <button className="btn-green" onClick={updatePet}>Update Pet</button>
              <button className="btn-gray" onClick={resetForm}>Cancel</button>
            </>
          )}
        </div>
      </div>

      <div>
        <h3>Get Pet By ID</h3>
        <input
          type="number"
          value={idToFetch}
          onChange={(e) => setIdToFetch(e.target.value)}
          placeholder="Enter ID"
        />
        <button className="btn-blue" onClick={getPetById}>Fetch</button>

        {fetchedPet && (
          <div>
            <h4>Pet Found:</h4>
            <pre>{JSON.stringify(fetchedPet, null, 2)}</pre>
          </div>
        )}
      </div>

      <div>
        <h3>All Pets</h3>
        {pets.length === 0 ? (
          <p>No pets found.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  {Object.keys(pet).map((key) => (
                    <th key={key}>{key}</th>
                  ))}
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {pets.map((p) => (
                  <tr key={p.id}>
                    {Object.keys(pet).map((key) => (
                      <td key={key}>{p[key]}</td>
                    ))}
                    <td>
                      <div className="action-buttons">
                        <button className="btn-green" onClick={() => handleEdit(p)}>Edit</button>
                        <button className="btn-red" onClick={() => deletePet(p.id)}>Delete</button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

    </div>
  );
};

export default PetManager;
