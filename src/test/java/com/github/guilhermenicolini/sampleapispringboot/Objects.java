package com.github.guilhermenicolini.sampleapispringboot;

import com.github.guilhermenicolini.sampleapispringboot.domain.Beer;
import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import com.github.guilhermenicolini.sampleapispringboot.dto.*;
import com.github.guilhermenicolini.sampleapispringboot.security.UserContext;
import com.github.guilhermenicolini.sampleapispringboot.utils.TimestampUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.List;

public class Objects {

    private Objects() {}

    private static final String USER_EMAIL = "john.doe@inbox.my";
    private static final String USER_NAME = "John Doe";
    private static final String USER_PASS = "123456";

    public static UserDto getUserDto() {
        return new UserDto(getUser());
    }

    public static User getUser() {
        User obj = new User();
        obj.setId("ac1a8db7-3a2d-4bc8-a0a0-35eb537d8944");
        obj.setEmail(USER_EMAIL);
        obj.setName(USER_NAME);
        obj.setPassword("$2a$10$U/cz5qHaFbMzxJDTzjOC4u6NwO7wHKUD6uniMdzCK8ddnl8BpFS7O");
        obj.setBeers(Collections.singletonList(getBeerNoUser()));
        return obj;
    }

    public static User getUser2() {
        User obj = new User();
        obj.setId("f6f07e1b-e01c-4ab1-802e-ea9530e3d727");
        obj.setEmail("jane.doe@inbox.my");
        obj.setName("Jane Doe");
        obj.setPassword("$2a$10$U/cz5qHaFbMzxJDTzjOC4u6NwO7wHKUD6uniMdzCK8ddnl8BpFS7O");
        return obj;
    }

    public static LoginDto getLoginDto() {
        LoginDto dto = new LoginDto();
        dto.setEmail(USER_EMAIL);
        dto.setPassword(USER_PASS);
        return dto;
    }

    public static CreateUserDto getCreateUserDto() {
        CreateUserDto dto = new CreateUserDto();
        dto.setEmail(USER_EMAIL);
        dto.setPassword(USER_PASS);
        dto.setName(USER_NAME);
        return dto;
    }

    public static UserContext getUserContext() {
        return new UserContext("ac1a8db7-3a2d-4bc8-a0a0-35eb537d8944", USER_EMAIL, USER_NAME);
    }

    public static UserContext getUserContext2() {
        return new UserContext("1cef1fdd-552c-4e0d-ab01-0ce5912ac670", "jane.doe@inbox.my", "Jane Doe");
    }

    public static UsernamePasswordAuthenticationToken getAuth() {
        return new UsernamePasswordAuthenticationToken(getUserContext(), null, null);
    }

    public static UsernamePasswordAuthenticationToken getAuth2() {
        return new UsernamePasswordAuthenticationToken(getUserContext2(), null, null);
    }

    public static List<BeerSummaryDto> getBeersSummary() {
        BeerSummaryDto dto = new BeerSummaryDto(getBeer());
        return Collections.singletonList(dto);
    }

    public static BeerDto getBeerDto() {
        return new BeerDto(getBeer());
    }

    public static Beer getBeer() {
        Beer obj = new Beer();
        obj.setId("f4b5ba26-96a9-4175-b3cc-b0e4440a0d01");
        obj.setBeerName("Beer Name");
        obj.setBrewer("Brewer");
        obj.setPrice(12.9);
        obj.setSampled(TimestampUtil.fromMillis(1573776000000L));
        obj.setHating(5);
        obj.setNotes("Notes");
        obj.setAbv(4.5);
        obj.setIbu(12);
        obj.setServingType("Cast");
        obj.setAlcohol(1);
        obj.setDarkFruit(2);
        obj.setCitrusFruit(3);
        obj.setHoppy(4);
        obj.setFloral(5);
        obj.setSpicy(6);
        obj.setHerbal(7);
        obj.setMalty(8);
        obj.setToffee(9);
        obj.setBurnt(10);
        obj.setSweet(11);
        obj.setSour(12);
        obj.setBitter(13);
        obj.setDry(14);
        obj.setBody(15);
        obj.setLinger(16);

        obj.setUser(getUser());

        return obj;
    }

    public static Beer getBeerNoUser() {
        Beer obj = new Beer();
        obj.setId("f4b5ba26-96a9-4175-b3cc-b0e4440a0d01");
        obj.setBeerName("Beer Name");
        obj.setBrewer("Brewer");
        obj.setPrice(12.9);
        obj.setSampled(TimestampUtil.fromMillis(1573776000000L));
        obj.setHating(5);
        obj.setNotes("Notes");
        obj.setAbv(4.5);
        obj.setIbu(12);
        obj.setServingType("Cast");
        obj.setAlcohol(1);
        obj.setDarkFruit(2);
        obj.setCitrusFruit(3);
        obj.setHoppy(4);
        obj.setFloral(5);
        obj.setSpicy(6);
        obj.setHerbal(7);
        obj.setMalty(8);
        obj.setToffee(9);
        obj.setBurnt(10);
        obj.setSweet(11);
        obj.setSour(12);
        obj.setBitter(13);
        obj.setDry(14);
        obj.setBody(15);
        obj.setLinger(16);

        return obj;
    }

    public static Beer getBeerUpdate(String id) {
        Beer obj = new Beer();
        obj.setId(id);
        obj.setBeerName("Beer Name 2");
        obj.setBrewer("Brewer 2");
        obj.setPrice(22.9);
        obj.setSampled(TimestampUtil.fromMillis(1563776000000L));
        obj.setHating(1);
        obj.setNotes("Notes 2");
        obj.setAbv(24.5);
        obj.setIbu(22);
        obj.setServingType("Cast 2");
        obj.setAlcohol(11);
        obj.setDarkFruit(12);
        obj.setCitrusFruit(13);
        obj.setHoppy(14);
        obj.setFloral(15);
        obj.setSpicy(16);
        obj.setHerbal(17);
        obj.setMalty(18);
        obj.setToffee(19);
        obj.setBurnt(110);
        obj.setSweet(111);
        obj.setSour(112);
        obj.setBitter(113);
        obj.setDry(114);
        obj.setBody(115);
        obj.setLinger(116);

        obj.setUser(getUser());

        return obj;
    }

    public static List<Beer> getBeers() {
        return Collections.singletonList(getBeer());
    }

    public static final String WRONG_TOKEN = "HPiuHPIUH)DS(*&gydg&*(DygD*&D)87YD0G*&DYg0D*&";

    public static final String NO_EXPIRATION_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJTYW1wbGUgQVBJIiwiYXVkIjoid3d3LmdpdGh1Yi5jb20vZ3VpbGhlcm1lbmljb2xpbmkiLCJzdWIiOiJqb2huLmRvZUBpbmJveC5teSIsImp0aSI6ImFjMWE4ZGI3LTNhMmQtNGJjOC1hMGEwLTM1ZWI1MzdkODk0NCIsIm5hbWUiOiJKb2huIERvZSIsImV4cCI6MjUzNDAyMzExNTk5fQ.V6YHA16PQPP6CWTxEKf06gmtZELL0HV1b_VX6-6lGwsVGS2oyE8t3ItxkLNwTxfUttyDBS4VzbK4hG6TE6msxw";

    public static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJTYW1wbGUgQVBJIiwiYXVkIjoid3d3LmdpdGh1Yi5jb20vZ3VpbGhlcm1lbmljb2xpbmkiLCJzdWIiOiJqb2huLmRvZUBpbmJveC5teSIsImp0aSI6IjA4ZWQ2NWNlLTYyYTYtNDc1My1hNjE0LWZlM2I1ZTJmOWY2MyIsIm5hbWUiOiJKb2huIERvZSIsImV4cCI6MTU3Mzc4MDAyN30.5vkZwP54JdsvOgDjQGnCXmQtADuIurgVavk1a-llo90CEIMXPy83FfgMKc7t6JE-ea--eleYNbueQEfkgpZ5Rg";

    public static final String INVALID_TOKEN ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
}
