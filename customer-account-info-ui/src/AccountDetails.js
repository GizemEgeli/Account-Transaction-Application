import React from 'react';

const AccountDetails = ({ accounts, onAccountClick }) => {
  return (
    <div>
      <h3>Accounts:</h3>
      <table className="table table-hover">
        <thead className="thead-light">
          <tr>
            <th>Account ID</th>
            <th>Balance</th>
          </tr>
        </thead>
        <tbody>
          {accounts.map((account) => (
            <tr key={account.id} onClick={() => onAccountClick(account)}>
              <td>{account.id}</td>
              <td>{account.balance}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AccountDetails;
