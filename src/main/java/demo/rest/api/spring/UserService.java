package demo.rest.api.spring;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/Services")
public class UserService {
    UserRepository repo = new UserRepository();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("use")
    public List<User> getUsers(){
        System.out.println("Calling!!");
        /*User user = new User(2,"jina","hiiiii","1999-10-10");
        Response response = Response.status(200).entity(user).build();*/
        return repo.getUserList();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{id}")
    public User getUser(@PathParam("id") int id){
        System.out.println("CALLLING");
        User user = repo.getUser(id);
      //  Response response = Response.status(200).entity(user).build();
       // System.out.println(repo.getUser(id));
        return repo.getUser(id);
    }
    //adduser(User user)
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User u1){
        System.out.println(u1);
        repo.create(u1);
        return u1;
    }
}
