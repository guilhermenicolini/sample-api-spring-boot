package com.github.guilhermenicolini.sampleapispringboot.dto;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DtoTest extends BaseTest {

    @Test
    public void constructors() {

        FlavorWheelDto fw = new FlavorWheelDto();
        assertNotNull(fw);

        UserDto u = new UserDto();
        assertNotNull(u);

        BeerSummaryDto bs = new BeerSummaryDto();
        assertNotNull(bs);
    }

}
