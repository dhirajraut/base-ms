package com.galaxy.spring.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.galaxy.spring.SimpleSpringBootWithJpaSupportApplication;

//import com.galaxy.spring.UserManagementApplication;


/* To inject Spring behavior */
@RunWith(SpringJUnit4ClassRunner.class)
/* To find autowire candidates */
@SpringBootTest(classes = SimpleSpringBootWithJpaSupportApplication.class)
public abstract class AbstractServiceTest
{

}