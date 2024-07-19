import axios from "axios";

const API_BASE_URL = "http://localhost:8081/api/v1/accounts";

export const getCustomerAccountInfo = async (customerId) => {
  try {
    const response = await axios.get(
      `${API_BASE_URL}/${customerId}/account-info`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching customer account info", error);
    throw error;
  }
};
