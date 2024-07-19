import React from 'react';

const TransactionDetails = ({ transactions }) => {
  return (
    <div>
      <h3>Transactions:</h3>
      <table className="table table-hover">
        <thead className="thead-light">
          <tr>
            <th>Reference ID</th>
            <th>Amount</th>
            <th>Type</th>
            <th>Description</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction) => (
            <tr key={transaction.referenceId}>
              <td>{transaction.referenceId}</td>
              <td>{transaction.amount}</td>
              <td>{transaction.transactionType}</td>
              <td>{transaction.description}</td>
              <td>{transaction.transactionDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TransactionDetails;