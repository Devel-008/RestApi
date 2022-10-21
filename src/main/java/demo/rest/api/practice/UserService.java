package demo.rest.api.practice;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@Path("/userService")
public class UserService {
    final Logger logger = LoggerFactory.getLogger(UserService.class);
    ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    UserRepository repo = context.getBean("userDao",UserRepository.class);
    //adduser(User user)
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User u1){
        System.out.println(u1);
        repo.create(u1,logger);
        return u1;
    }

}
