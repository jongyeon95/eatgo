package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Region;
import kr.co.fastcampus.eatgo.domain.RegionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RegionServiceTest {


    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        regionService=new RegionService(regionRepository);
    }

    @Test
    public void getRegions(){
        List<Region> MockRegion=new ArrayList<>();
        MockRegion.add(Region.builder().name("Seoul").build());
        given(regionRepository.findAll()).willReturn(MockRegion);
        List<Region> regions=regionService.getRegions();

        Region region=regions.get(0);
        assertThat(region.getName(),is("Seoul"));
    }

    @Test
    public void addRegion(){
        Region region=regionService.addRegion("Seoul");
        verify(regionRepository).save(any());
        assertThat(region.getName(),is("Seoul"));
    }
}