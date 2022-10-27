package demo.rest.api.spring;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Path("/userService")
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    UserRepository repo = context.getBean("userDao",UserRepository.class);

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User u1){
        logger.info("Logger intialized to create!!!");
        Response response = Response.status(200).entity(u1).build();
        repo.create(u1,logger);
        logger.info(String.valueOf(response));
        return response;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("use")
    public Response getUsers(){
        logger.info("Logger intialized to Get User");
        Response response = Response.status(200).entity(repo.getUserList(logger)).build();
        return response;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{id}")
    public Response getUser(@PathParam("id") int id){
        logger.info("Logger intialized to Random User");
        User user = repo.getUser(id,logger);
        Response response = Response.status(200).entity(user).build();
        // System.out.println(repo.getUser(id));
        return response;
    }
    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") int id){
        logger.info("Logger intialized to Delete");
        User user = repo.delete(id, logger);
        Response response = Response.status(200).entity(user).build();
        // System.out.println(repo.getUser(id));
        return response;
    }
    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(User user){
        logger.info("Logger intialized to Update");
        System.out.println(user);
        Response response = Response.status(200).entity(repo.update(logger,user)).build();
        // System.out.println(repo.getUser(id));
        return response;
    }

}
