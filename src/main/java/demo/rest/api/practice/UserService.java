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
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("use")
    public List<User> getUsers(){
        logger.info("Logger Intialized to getUsers");
        /*User user = new User(2,"jina","hiiiii","1999-10-10");
        Response response = Response.status(200).entity(user).build();*/
        return repo.getUserList();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{id}")
    public User getUser(@PathParam("id") int id){
        User user = repo.getUser(id);
      //  Response response = Response.status(200).entity(user).build();
       // System.out.println(repo.getUser(id));
        repo.tearDown();
        return user;

    }
    //adduser(User user)
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User u1){
        System.out.println(u1);
        repo.create(u1,logger);
        return u1;
    }
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public User update(User u1){
        System.out.println(u1);
        if(repo.getUser(u1.getId()).getId()==0){
            repo.create(u1,logger);
        }else {
            repo.update(u1);
        }
        return u1;
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{id}")
    public User delete(@PathParam("id") int id){
        User user = repo.getUser(id);
        //  Response response = Response.status(200).entity(user).build();
        // System.out.println(repo.getUser(id));
        if(user.getId() != 0)
        repo.delete(id);
        return user;
    }
}
