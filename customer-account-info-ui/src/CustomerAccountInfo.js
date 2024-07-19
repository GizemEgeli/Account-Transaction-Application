import React, { useState } from "react";
import { getCustomerAccountInfo } from "./CustomerAccountInfoApiService";
import AccountDetails from "./AccountDetails";
import TransactionDetails from "./TransactionDetails";
import CreateAccount from "./CreateAccount";

const CustomerAccountInfo = () => {
  const [customerId, setCustomerId] = useState("");
  const [customerInfo, setCustomerInfo] = useState(null);
  const [error, setError] = useState(null);
  const [selectedAccount, setSelectedAccount] = useState(null);

  const handleFetchCustomerAccountInfo = async (id) => {
    try {
      const data = await getCustomerAccountInfo(id);
      setCustomerInfo(data);
      setError(null);
      setSelectedAccount(null);
    } catch (err) {
      setError("Failed to fetch customer account info");
      setCustomerInfo(null);
      setSelectedAccount(null);
    }
  };

  const handleAccountCreated = () => {
    if (customerId) {
      handleFetchCustomerAccountInfo(customerId);
    }
  };

  return (
    <div className="container">
      <div className="row mt-3">
        <div className="col-md-4">
          <CreateAccount onAccountCreated={handleAccountCreated} />
        </div>
        <div className="col-md-8">
          <h4>Customer Account Information</h4>
          <div className="input-group mb-2">
            <input
              type="text"
              className="form-control"
              value={customerId}
              onChange={(e) => setCustomerId(e.target.value)}
              placeholder="Customer ID"
              style={{ fontSize: '0.875rem' }}
            />
            <button className="btn btn-primary" onClick={() => handleFetchCustomerAccountInfo(customerId)} style={{ fontSize: '0.875rem' }}>
              Retrieve Accounts
            </button>
          </div>
          {error && <p className="text-danger" style={{ fontSize: '0.875rem' }}>{error}</p>}
        </div>
      </div>
      {customerInfo && (
        <div className="row mt-3">
          <div className="col-md-6">
            <h5>{customerInfo.name} {customerInfo.surname}</h5>
            <AccountDetails accounts={customerInfo.accounts} onAccountClick={setSelectedAccount} />
          </div>
          {selectedAccount && (
            <div className="col-md-6">
              <TransactionDetails transactions={selectedAccount.transactions} />
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default CustomerAccountInfo;
