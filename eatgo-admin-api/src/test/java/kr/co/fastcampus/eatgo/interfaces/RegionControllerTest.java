package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RegionService;
import kr.co.fastcampus.eatgo.domain.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(RegionController.class)
public class RegionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private RegionService regionService;

    @Test
    public void list() throws Exception {
        List<Region> regions= new ArrayList<>();
        regions.add(Region.builder().name("Seoul").build());
        given(regionService.getRegions()).willReturn(regions);
        mvc.perform(get("/regions")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Seoul")));
    }

    @Test
    public  void create() throws Exception {
        Region region=Region.builder().name("Seoul").build();
        given(regionService.addRegion("Seoul")).willReturn(region);
        mvc.perform(post("/regions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"));

        verify(regionService).addRegion("Seoul");
    }

}