package com.sqa.bhxh.controller;

import com.sqa.bhxh.entities.Enterprise;
import com.sqa.bhxh.repository.EnterpriseRepository;
import com.sqa.bhxh.services.EnterpriseService;
import com.sqa.bhxh.services.impl.EnterpriseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EnterpriseController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EnterpriseControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @TestConfiguration
    public static class ServiceTestConfiguration {
        @Bean
        EnterpriseService enterpriseService() {
            return new EnterpriseServiceImpl();
        }

    }

    @Autowired
    private MockMvc mockMvc;

    //repository
    @MockBean
    private EnterpriseRepository enterpriseRepository;

    private String jsonCreateEnterprise = "{\n" +
            "            \"taxCode\": \"1\",\n" +
            "            \"name\": \"abc\",\n" +
            "            \"address\": \"xyz\",\n" +

            "            \"user\": \n" +
            "               {\n" +
            "            \"id\": \"1\"\n" +
            "        }\n" +
            "        }";

    private String jsonUpdateEnterprise = "{\n" +
            "              \"taxCode\": \"1\",\n" +
            "              \"id\": \"1\",\n" +
            "            \"taxCode\": \"1\",\n" +
            "            \"name\": \"abc\",\n" +
            "            \"address\": \"xyz\",\n" +

            "            \"user\": \n" +
            "               {\n" +
            "            \"id\": \"1\"\n" +
            "        }\n" +
            "        }";


    @Test
    public void testGetListEnterpriseSuccess() throws Exception {
        List<Enterprise> enterprises = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Enterprise enterprise = new Enterprise();
            enterprise.setId(i);
            enterprises.add(enterprise);
        }
        List<Enterprise> result = new ArrayList<>(enterprises);

        given(enterpriseRepository.findAll()).willReturn(result);
        mockMvc.perform(get("/enterprise/get-all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateEnterpriseWithExistTaxCode() throws Exception {
        Enterprise enterprise = new Enterprise();
        enterprise.setId(1);
        enterprise.setTaxCode("1");
        given(enterpriseRepository.findByTaxCode("1")).willReturn(enterprise);
        given(enterpriseRepository.save(isA(Enterprise.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(post("/enterprise/create")
                .contentType(APPLICATION_JSON_UTF8).content(jsonCreateEnterprise))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400));

    }

    @Test
    public void testCreateEnterpriseSuccess() throws Exception {
        given(enterpriseRepository.save(isA(Enterprise.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(post("/enterprise/create")
                .contentType(APPLICATION_JSON_UTF8).content(jsonCreateEnterprise))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("abc"));

    }

    @Test
    public void testUpdateEnterpriseSuccess() throws Exception {
        Enterprise enterprise = new Enterprise();
        enterprise.setId(1);
        given(enterpriseRepository.findById(1)).willReturn(enterprise);
        given(enterpriseRepository.save(isA(Enterprise.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(patch("/enterprise/update")
                .contentType(APPLICATION_JSON_UTF8).content(jsonUpdateEnterprise))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("abc"));

    }

    @Test
    public void testUpdateEnterpriseWithNotFoundEnterprise() throws Exception {
        given(enterpriseRepository.findById("1")).willReturn(Optional.empty());
        given(enterpriseRepository.save(isA(Enterprise.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(patch("/enterprise/update")
                .contentType(APPLICATION_JSON_UTF8).content(jsonUpdateEnterprise))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(404));

    }
}
