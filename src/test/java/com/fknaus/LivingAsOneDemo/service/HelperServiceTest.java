package com.fknaus.LivingAsOneDemo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class HelperServiceTest {

    @Value("${livingAsOneDemo.datesFile}")
    private String datesFile;

    @Test
    public void parseDateTest1() {
        String input = "02/27/17";
        String expected = "2017-02-27";
        assertEquals("date parsing test 1", expected, HelperService.parseDate(input));
    }

    @Test
    public void parseDateTest2() {
        String input = "June 2, 2018";
        String expected = "2018-06-02";
        assertEquals("date parsing test 2", expected, HelperService.parseDate(input));
    }

    @Test
    public void parseDateTest3() {
        String input = "Jul-13-2016";
        String expected = "2016-07-13";
        assertEquals("date parsing test 2", expected, HelperService.parseDate(input));
    }

    @Test
    public void parseDateTestReturnsEmptyStringOnIncorrectDate() {
        String input = "April 31, 2018";
        String expected = "";
        assertEquals("date parsing test 1", expected, HelperService.parseDate(input));
    }

    @Test
    public void testGetDatesFromFile() {
        List<String> result = HelperService.getDatesFromFile(datesFile);
        assertNotNull("the dates file is not null", result);
        assertEquals("four dates are returned",4, result.size());
        assertEquals("each row is a string value", String.class, result.get(0).getClass());
    }

    @Test
    public void determineDateFormatFindsMatchingFormat() {
        String input = "02/27/17";
        String expected = "MM/dd/yy";
        assertEquals("find matching date format", expected, HelperService.determineDateFormat(input));
    }

}