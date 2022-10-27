package demo.rest.api.form;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/product")
public class ProductService{
    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    public Response addUser(
            @FormParam("id") int id,
            @FormParam("name") String name,
            @FormParam("price") float price) {

        return Response.status(200)
                .entity(" Product added successfuly!<br> Id: "+id+"<br> Name: " + name+"<br> Price: "+price)
                .build();
    }
}
