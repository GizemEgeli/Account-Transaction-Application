package com.company.customeraccountsapi.controller;

import com.company.customeraccountsapi.model.dto.CreateAccountRequest;
import com.company.customeraccountsapi.model.dto.UserAccountInfoDTO;
import com.company.customeraccountsapi.service.AccountService;
import com.company.customeraccountsapi.service.UserAccountInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerAccountController.class)
class CustomerAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private UserAccountInfoService userAccountInfoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAccount() throws Exception {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(1L, BigDecimal.valueOf(100));

        mockMvc.perform(post("/api/v1/accounts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAccountRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetUserAccountInfo() throws Exception {
        UserAccountInfoDTO userAccountInfoDTO = new UserAccountInfoDTO("Gizem", "Egeli", Collections.emptyList());
        when(userAccountInfoService.getUserAccountInfo(1L)).thenReturn(userAccountInfoDTO);

        mockMvc.perform(get("/api/v1/accounts/1/account-info")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gizem"))
                .andExpect(jsonPath("$.surname").value("Egeli"))
                .andExpect(jsonPath("$.accounts").isEmpty());
    }
}
