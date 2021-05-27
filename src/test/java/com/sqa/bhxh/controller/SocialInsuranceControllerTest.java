package com.sqa.bhxh.controller;

import com.sqa.bhxh.entities.SocialInsurance;
import com.sqa.bhxh.repository.SocialInsuranceRepository;
import com.sqa.bhxh.services.SocialInsuranceService;
import com.sqa.bhxh.services.impl.SocialInsuranceServiceImpl;
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
@WebMvcTest(SocialInsuranceController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SocialInsuranceControllerTest {


    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @TestConfiguration
    public static class ServiceTestConfiguration {
        @Bean
        SocialInsuranceService socialInsuranceService() {
            return new SocialInsuranceServiceImpl();
        }

    }

    @Autowired
    private MockMvc mockMvc;

    //repository
    @MockBean
    private SocialInsuranceRepository socialInsuranceRepository;

    private String jsonCreateSocialInsurance = "{\n" +
            "            \"name\": \"1\",\n" +
            "            \"category\": \"abc\"\n" +
            "        }";

    private String jsonUpdateSocialInsurance = "{\n" +
            "            \"id\": \"1\",\n" +
            "            \"name\": \"1\",\n" +
            "            \"category\": \"abc\"\n" +
            "        }";

    @Test
    public void testGetListSocialInsuranceSuccess() throws Exception {
        List<SocialInsurance> socialInsurances = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            SocialInsurance socialInsurance = new SocialInsurance();
            socialInsurance.setId(i);
            socialInsurances.add(socialInsurance);
        }
        List<SocialInsurance> result = new ArrayList<>(socialInsurances);

        given(socialInsuranceRepository.findAll()).willReturn(result);
        mockMvc.perform(get("/social-insurance/get-all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateSocialInsuranceSuccess() throws Exception {
        given(socialInsuranceRepository.save(isA(SocialInsurance.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(post("/social-insurance/create")
                .contentType(APPLICATION_JSON_UTF8).content(jsonCreateSocialInsurance))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("1"));

    }

    @Test
    public void testUpdateSocialInsuranceSuccess() throws Exception {
        SocialInsurance socialInsurance = new SocialInsurance();
        socialInsurance.setId(1);
        given(socialInsuranceRepository.findById(1)).willReturn(socialInsurance);
        given(socialInsuranceRepository.save(isA(SocialInsurance.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(patch("/social-insurance/update")
                .contentType(APPLICATION_JSON_UTF8).content(jsonUpdateSocialInsurance))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("1"));

    }

    @Test
    public void testUpdateSocialInsuranceWithNotFoundSocialInsurance() throws Exception {
        given(socialInsuranceRepository.findById("1")).willReturn(Optional.empty());
        given(socialInsuranceRepository.save(isA(SocialInsurance.class))).willAnswer(i -> i.getArgument(0));
        mockMvc.perform(patch("/social-insurance/update")
                .contentType(APPLICATION_JSON_UTF8).content(jsonUpdateSocialInsurance))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(404));

    }
}
