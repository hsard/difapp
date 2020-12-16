package com.waes.diffapp.controller;

import static com.waes.diffapp.constants.DiffStatus.MATCHING_CONTENT;
import static com.waes.diffapp.constants.DiffStatus.MISMATCHING_SIZES;
import static com.waes.diffapp.constants.DiffStatus.NOT_BASE64_CONTENT;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.waes.diffapp.DiffAppApplication;
import com.waes.diffapp.constants.DiffSide;
import com.waes.diffapp.constants.DiffStatus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = DiffAppApplication.class)
public class ApiControllerTest {

    private static final String SESSION_ID="123";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void post_blank_content_test() throws Exception {
        postSideContent(SESSION_ID, " ", DiffSide.LEFT)
            .andExpect(status().isBadRequest());
    }

    @Test
    void post_not_blank_content_test() throws Exception {
        postSideContent(SESSION_ID, "{\"content\":\"adasdasdas\"}", DiffSide.LEFT)
            .andExpect(status().isAccepted());
    }

    @Test
    void post_diff_length_contents_test() throws Exception {
        String sideOneContent = "{\"content\":\"adasdasdas\"}";
        String sideTwoContent = "{\"content\":\"adasdasdasaaa\"}";

        postBothSideContents(sideOneContent, sideTwoContent);
        getDiffResultAndExpectResult(MISMATCHING_SIZES);
    }

    @Test
    void post_not_64_encoding_contents_test() throws Exception {
        String sideOneContent = "{\"content\":\"ad.asda.sdas\"}";
        String sideTwoContent = "{\"content\":\"adasd.asdas.aaa\"}";

        postBothSideContents(sideOneContent, sideTwoContent);
        getDiffResultAndExpectResult(NOT_BASE64_CONTENT);
    }

    @Test
    void post_matching_contents_test() throws Exception {
        String sideOneContent = "{\"content\":\"asdqwezxc\"}";
        String sideTwoContent = "{\"content\":\"asdqwezxc\"}";
        postBothSideContents(sideOneContent, sideTwoContent);
        getDiffResultAndExpectResult(MATCHING_CONTENT);
    }

    @Test
    void post_mismatching_contents_test() throws Exception {
        String sideOneContent = "{\"content\":\"abcdefgh\"}";
        String sideTwoContent = "{\"content\":\"abERefTY\"}";
        postBothSideContents(sideOneContent, sideTwoContent);
        getDiffResult(SESSION_ID)
            .andExpect(status().isOk())
            .andExpect(content().json("{'resultCode':'MISMATCHING_CONTENT','resultDescription':'Mismatching contents','diffs':[{'start':2,'offset':2},{'start':6,'offset':2}]}"));
    }

    public ResultActions getDiffResult(String sessionId) throws Exception {
        return mockMvc.perform(get("/v1/diff/{id}", sessionId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
    }

    public ResultActions postSideContent(String sessionId, String content, DiffSide diffSide) throws Exception {
        return mockMvc.perform(
            post("/v1/diff/{id}/{diffSide}", sessionId, diffSide.name())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));
    }

    public void postBothSideContents(String content1, String content2) throws Exception {
        postSideContent(SESSION_ID, content1, DiffSide.LEFT)
            .andExpect(status().isAccepted());

        postSideContent(SESSION_ID, content2, DiffSide.RIGHT)
            .andExpect(status().isAccepted());
    }

    public void getDiffResultAndExpectResult(DiffStatus expectedStatus) throws Exception{
        getDiffResult(SESSION_ID)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.resultCode", is(expectedStatus.name())));
    }
}
