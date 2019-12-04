package com.github.guilhermenicolini.sampleapispringboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationTest {

    @Test
    public void main() {
        String[] args = new String[]{};
        SampleApiSpringBootApplication.main(args);
        assertNotNull(args);
    }

}
