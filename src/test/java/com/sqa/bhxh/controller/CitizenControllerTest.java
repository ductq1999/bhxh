package com.sqa.bhxh.controller;

import com.sqa.bhxh.entities.Citizen;
import com.sqa.bhxh.repository.CitizenRepository;
import com.sqa.bhxh.services.CitizenService;
import com.sqa.bhxh.services.impl.CitizenServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CitizenController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CitizenControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @TestConfiguration
    public static class ServiceTestConfiguration {
        @Bean
        CitizenService citizenService() {
            return new CitizenServiceImpl();
        }

    }

    @Autowired
    private MockMvc mockMvc;

    //repository
    @MockBean
    private CitizenRepository citizenRepository;

    private String jsonCreateCitizen= "{\n" +
            "            \"identityNumber\": \"1\",\n" +
            "            \"fullName\": \"abc\",\n" +
            "            \"address\": \"xyz\",\n" +
            "            \"enterprise\": \n" +
            "               {\n" +
            "            \"id\": \"1\"\n" +
            "        },\n" +
            "            \"socialInsurance\": \n" +
            "               {\n" +
            "            \"id\": \"1\"\n" +
            "        },\n" +
            "            \"user\": \n" +
            "               {\n" +
            "            \"id\": \"1\"\n" +
            "        },\n" +
            "            \"l\": \"1000\",\n" +
            "            \"pc1\": \"1000\",\n" +
            "            \"pc2\": \"1000\",\n" +
            "            \"pc3\": \"1000\",\n" +
            "            \"pc4\": \"1000\",\n" +
            "            \"pc5\": \"1000\",\n" +
            "            \"pc6\": \"1000\",\n" +
            "            \"7\": \"1000\"\n" +
            "        }";

    private String jsonUpdateCitizen= "{\n" +
            "            \"id\": \"1\",\n" +
            "            \"identityNumber\": \"1\",\n" +
            "            \"fullName\": \"abc\",\n" +
            "            \"address\": \"xyz\",\n" +
            "            \"enterprise\": \n" +
            "               {\n" +
            "            \"id\": \"1\"\n" +
            "        },\n" +
            "            \"socialInsurance\": \n" +
            "               {\n" +
            "            \"id\": \"1\"\n" +
            "        },\n" +
            "            \"user\": \n" +
            "               {\n" +
            "            \"id\": \"1\"\n" +
            "        },\n" +
            "            \"l\": \"1000\",\n" +
            "            \"pc1\": \"1000\",\n" +
            "            \"pc2\": \"1000\",\n" +
            "            \"pc3\": \"1000\",\n" +
            "            \"pc4\": \"1000\",\n" +
            "            \"pc5\": \"1000\",\n" +
            "            \"pc6\": \"1000\",\n" +
            "            \"7\": \"1000\"\n" +
            "        }";

    @Test
    public void testGetListCitizenSuccess() throws Exception {
        List<Citizen> citizens = new ArrayList<>();
        for(int i=1;i<5;i++){
            Citizen citizen = new Citizen();
            citizen.setId(i);
            citizens.add(citizen);
        }
        List<Citizen> result =new ArrayList<>(citizens);

        given(citizenRepository.findAll()).willReturn( result);
        mockMvc.perform(get("/citizen/get-all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateCitizenWithExistIdentityNumber()throws Exception{
        Citizen citizen= new Citizen();
        citizen.setId(1);
        citizen.setIdentityNumber("1");
        given(citizenRepository.findByIdentityNumber("1")).willReturn(citizen);
        given(citizenRepository.save(isA(Citizen.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(post("/citizen/create")
                .contentType(APPLICATION_JSON_UTF8).content(jsonCreateCitizen))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400));

    }

    @Test
    public void testCreateCitizenSuccess()throws Exception{
        given(citizenRepository.save(isA(Citizen.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(post("/citizen/create")
                .contentType(APPLICATION_JSON_UTF8).content(jsonCreateCitizen))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fullName").value("abc"));
    }

    @Test
    public void testUpdateCitizenSuccess()throws Exception{
        Citizen citizen= new Citizen();
        citizen.setId(1);
        given(citizenRepository.findById(1)).willReturn(citizen);
        given(citizenRepository.save(isA(Citizen.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(patch("/citizen/update")
                .contentType(APPLICATION_JSON_UTF8).content(jsonUpdateCitizen))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fullName").value("abc"));
    }

    @Test
    public void testUpdateCitizenWithNotFoundCitizen()throws Exception{
        given(citizenRepository.findById("1")).willReturn(Optional.empty());
        given(citizenRepository.save(isA(Citizen.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(patch("/citizen/update")
                .contentType(APPLICATION_JSON_UTF8).content(jsonUpdateCitizen))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(404));


    }
}
