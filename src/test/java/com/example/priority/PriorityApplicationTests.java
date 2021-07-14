package com.example.priority;

import com.example.priority.repository.UserRepository;
import com.example.priority.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class PriorityApplicationTests {

	@Value("${ranjeet}")
	private String values;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		System.out.println(values);
	}

}
