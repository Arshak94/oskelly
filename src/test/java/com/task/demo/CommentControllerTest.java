package com.task.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.demo.dto.CommentDto;
import com.task.demo.entity.Comment;
import com.task.demo.error.ErrorResource;
import com.task.demo.repo.CommentRepository;
import com.task.demo.repo.NotificationRepository;
import com.task.demo.response.CommentResponse;
import com.task.demo.service.impl.CommentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskApplication.class)
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CommentServiceImpl commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void create() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < 50; i++) {
            UUID uuid = UUID.randomUUID();
            CommentDto commentDto = new CommentDto("create comment" + uuid);
            MvcResult mvcResult = mockMvc.perform(post("/comments")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .content(commentDto.toString()))
                    .andReturn();
            if (mvcResult.getResponse().getStatus() == 201) {
                CommentResponse commentResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), CommentResponse.class);
                Assert.assertEquals(commentRepository.findById(commentResponse.getId()).map(Comment::getComment).orElse(null), commentDto.getComment());
                Assert.assertNotNull(notificationRepository.findByCommentId(commentResponse.getId()));
            }

            if (mvcResult.getResponse().getStatus() == 400) {
                ErrorResource error = mapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorResource.class);
                Assert.assertEquals(commentRepository.findById(error.getId()), Optional.empty());
                Assert.assertNull(notificationRepository.findByCommentId(error.getId()));
            }
        }
        System.out.println("Success save comment percentage: " + calculatePercent(50, commentRepository.count()));
        System.out.println("Success delivered notifications percentage: " + calculatePercent(notificationRepository.count(), notificationRepository.countByDelivered(true)));
    }

    private double calculatePercent(double all, double percentCount){
        return (percentCount * 100) / all;
    }
}
