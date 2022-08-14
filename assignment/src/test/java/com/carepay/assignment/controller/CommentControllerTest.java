package com.carepay.assignment.controller;

import com.hackerrank.test.utility.Order;
import com.hackerrank.test.utility.OrderedTestRunner;
import com.hackerrank.test.utility.TestWatchman;
import junit.framework.TestCase;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(OrderedTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest extends TestCase {

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
        TestWatchman.watchman.registerClass(CommentControllerTest.class);
    }

    @AfterClass
    public static void tearDownClass() {
        TestWatchman.watchman.createReport(CommentControllerTest.class);
    }


    @Test
    @Order(1)
    public void testAddComment() {
    }

    @Test
    @Order(2)
    public void testUpdateComment() {
    }

    @Test
    @Order(3)
    public void testGetCommentsByPostId() {
    }

    @Test
    @Order(4)
    public void testDeleteComment() {
    }
}