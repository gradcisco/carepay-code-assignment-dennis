package com.carepay.assignment.controller;

import com.hackerrank.test.utility.Order;
import com.hackerrank.test.utility.OrderedTestRunner;
import com.hackerrank.test.utility.TestWatchman;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(OrderedTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

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
        System.out.println("*****************Hello there*************");
        TestWatchman.watchman.registerClass(UserControllerTest.class);
    }

    @AfterClass
    public static void tearDownClass() {
        TestWatchman.watchman.createReport(UserControllerTest.class);
    }


    /**
     * Cannot access without a token
     * @throws Exception
     *
     *     MvcResult result = mockMvc.perform(post("/api/tracker/jobs/work")
     *                     .contentType(TestUtil.APPLICATION_JSON_UTF8)
     *                     .content(TestUtil.convertObjectToJsonBytes(workRequest)))
     *             .andExpect(status().isCreated())
     *             .andReturn();
     *
     *     String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id")
     */
    @Test
    @Order(1)
    public void testGetUsers() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(HttpHeaders.CACHE_CONTROL);

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

         json = "{\n" +
                "  \"password\": \"123\",\n" +
                "  \"username\": \"chris\"\n" +
                "}";

        MvcResult result = mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(json)
                )
                .andExpect(status().isOk()).andReturn();



        String token = result.getResponse().getContentAsString().split(":")[1].replace("}","");
        System.out.println("***********************\n" + token + "\n\n\n************************");
        token = "Bearer " + token.substring(1,token.length()-1);
        System.out.println("***********************\n" + token + "\n\n\n************************");

        mockMvc.perform(
                get("/users").header("Authorization",token))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testGetUsersById() {
    }

    @Test
    @Order(3)
    public void testDeleteUserById() {
    }

    @Test
    @Order(4)
    public void testDeleteUsers() {
    }

    @Test
    @Order(5)
    public void testUpdateUser() {
    }



}