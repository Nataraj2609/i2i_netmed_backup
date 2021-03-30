package com.netmed.usermodule.controller;

import com.netmed.usermodule.dto.UserDto;
import com.netmed.usermodule.model.Role;
import com.netmed.usermodule.model.User;
import com.netmed.usermodule.service.UserService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UserControllerTest class is used to do Mockmvc test - Web layer test for UserController
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    /* The mockMvc*/
    @Autowired
    private MockMvc mockMvc;

    /* The userService*/
    @MockBean
    UserService userService;

    /* The ModelMapper */
    @Autowired
    private ModelMapper modelMapper;

    /* The LoggerFactory */
    private Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    /**
     * contextLoads is used to do test application context
     *
     * @throws Exception
     */
    @Test
    public void contextLoads() throws Exception {
        log.info(String.valueOf(mockMvc.getDispatcherServlet()));
    }

    /**
     * createUserTest is used to test createUser() rest End point
     *
     * @throws Exception
     */
    @Test
    public void createUserTest() throws Exception {
        Role role = new Role(2, "Patient");
        User user = new User(0, "SivaKarthik", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        when(userService.createUser(userDto)).thenReturn(userDto);
        MockHttpServletResponse response = this.mockMvc.perform(post("/netmed-user-api/v1/users")
                .content("{ \"userName\":\"SivaKarthik\",\"password\":\"abcdefghi\",\"roleName\":\"Patient\",\"createdBy\":\"Doctor Hari\",\"createdDate\":\"2021-03-19T16:17:30.0164135\",\"lastModifiedBy\":\"Doctor Hari\",\"lastModifiedDate\":\"2021-03-19T16:17:30.0164135\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName").value("SivaKarthik"))
                .andExpect(status().isCreated()).andReturn().getResponse();
        log.info(response.getContentAsString());
    }

    /**
     * getUserTest is used to test getUser() rest End point
     *
     * @throws Exception
     */
    @Test
    public void getUserTest() throws Exception {
        Role role = new Role(2, "Patient");
        User user = new User(0, "SivaKarthik", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        when(userService.getUser(Long.valueOf(2))).thenReturn(userDto);
        MockHttpServletResponse response = this.mockMvc.perform(get("/netmed-user-api/v1/users/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();
        log.info(response.getContentAsString());
    }

    /**
     * updateUserTest is used to test updateUser() rest End point
     *
     * @throws Exception
     */
    @Test
    public void updateUserTest() throws Exception {
        Role role = new Role(2, "Patient");
        User user = new User(0, "SivaKarthik", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        when(userService.updateUser(userDto)).thenReturn(userDto);
        MockHttpServletResponse response = this.mockMvc.perform(put("/netmed-user-api/v1/users")
                .content("{ \"userName\":\"SivaKarthik\",\"password\":\"abcdefghi\",\"roleName\":\"Patient\",\"createdBy\":\"Doctor Hari\",\"createdDate\":\"2021-03-19T16:17:30.0164135\",\"lastModifiedBy\":\"Doctor Hari\",\"lastModifiedDate\":\"2021-03-19T16:17:30.0164135\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName").value("SivaKarthik"))
                .andExpect(status().isOk()).andReturn().getResponse();
        log.info(response.getContentAsString());
    }

    /**
     * deleteUserTest is used to test deleteUser() rest End point
     *
     * @throws Exception
     */
    @Test
    public void deleteUserTest() throws Exception {
        this.mockMvc.perform(delete("/netmed-user-api/v1/users/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andReturn().getResponse();
    }

    /**
     * getAllUsersTest is used to test getAllUsers() rest End point
     *
     * @throws Exception
     */
    @Test
    public void getAllUsersTest() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/netmed-user-api/v1/users/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();
        log.info(response.getContentAsString());
    }

    /**
     * searchUserTest is used to test searchUser() rest End point
     *
     * @throws Exception
     */
    @Test
    public void searchUserTest() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/netmed-user-api/v1/users/searchUser")
                .param("search", "H")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();
        log.info(response.getContentAsString());
    }

    /**
     * searchTest is used to test search() rest End point
     *
     * @throws Exception
     */
    @Test
    public void searchTest() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/netmed-user-api/v1/users/search")
                .param("search", "H")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();
        log.info(response.getContentAsString());
    }

    /**
     * getUserIdByUserNameTest is used to test getUserIdByUserName() rest End point
     *
     * @throws Exception
     */
    @Test
    public void getUserIdByUserNameTest() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/netmed-user-api/v1/users/findUserId")
                .param("userName", "John Wick")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();
        log.info(response.getContentAsString());
    }

    /**
     * elasticSearchTest is used to test elasticSearch() rest End point
     *
     * @throws Exception
     */
    @Test
    public void elasticSearchTest() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/netmed-user-api/v1/users/doElasticSearch")
                .param("q", "John Wick")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();
        log.info(response.getContentAsString());
    }
}