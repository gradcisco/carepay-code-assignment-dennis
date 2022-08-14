package com.carepay.assignment.controller;
import com.fasterxml.jackson.core.JsonParser;
import com.hackerrank.test.utility.Order;
import com.hackerrank.test.utility.OrderedTestRunner;
import com.hackerrank.test.utility.ResultMatcher;
import com.hackerrank.test.utility.TestWatchman;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(OrderedTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JwtControllerTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public TestWatcher watchman = TestWatchman.watchman;

    @Autowired
    private MockMvc mockMvc;

   @BeforeClass
    public static void setUpClass() {
        TestWatchman.watchman.registerClass(JwtControllerTest.class);
    }

    @AfterClass
    public static void tearDownClass() {
        TestWatchman.watchman.createReport(JwtControllerTest.class);
    }

    @Test
    @Order(1)
    public void createUser() throws Exception {
        String json = "{\n" +
                "  \"userName\": \"chris\",\n" +
                "  \"userPassword\": \"123\"\n" +
                "}";

        mockMvc.perform(
                        put("/login")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(json)
                )
                .andExpect(status().isCreated());

    }

    @Test
    @Order(2)
    public void createToken() throws Exception {
        String json = "{\n" +
                "  \"password\": \"123\",\n" +
                "  \"username\": \"chris\"\n" +
                "}";

       MvcResult result = mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(json)
                )
                .andExpect(status().isOk()).andReturn();



        System.out.println("***********************\n" + result.getResponse().getContentAsString().split(":")[1].replace("}","") + "\n\n\n************************");
    }


}