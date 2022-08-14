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
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        TestWatchman.watchman.registerClass(UserControllerTest.class);
    }

    @AfterClass
    public static void tearDownClass() {
        TestWatchman.watchman.createReport(UserControllerTest.class);
    }


    /**
     * Cannot access without a token
     * @throws Exception
     */
    @Test
    @Order(1)
    public void testGetUsers() throws Exception {
        mockMvc.perform(
                get("/users"))
                .andExpect(status().isUnauthorized());
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