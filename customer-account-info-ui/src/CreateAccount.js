import React, { useState } from 'react';
import axios from 'axios';

const CreateAccount = ({ onAccountCreated }) => {
  const [customerId, setCustomerId] = useState('');
  const [initialCredit, setInitialCredit] = useState('');
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

    try {
      const response = await axios.post('http://localhost:8081/api/v1/accounts', {
        customerId: Number(customerId),
        initialCredit: parseFloat(initialCredit),
      });
      if (response.status === 201) {
        onAccountCreated(customerId);
        setCustomerId('');
        setInitialCredit('');
      }
    } catch (err) {
      setError('Failed to create account');
    }
  };

  return (
    <div className="card mt-4">
      <div className="card-header">
        <h3>Create New Account</h3>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="customerId" className="form-label">Customer ID</label>
            <input
              type="number"
              className="form-control"
              id="customerId"
              value={customerId}
              onChange={(e) => setCustomerId(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="initialCredit" className="form-label">Initial Credit</label>
            <input
              type="number"
              className="form-control"
              id="initialCredit"
              value={initialCredit}
              onChange={(e) => setInitialCredit(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="btn btn-primary">Create Account</button>
        </form>
      </div>
    </div>
  );
};

export default CreateAccount;
