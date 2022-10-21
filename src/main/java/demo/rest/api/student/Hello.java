package demo.rest.api.student;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("/hello")
public class Hello {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/helloJson")
    public String helloJSON(){
        String resource = "Hello JSON";
        return resource;
    }
    /*@GET
    @Path("/helloHtml")
    @Produces(MediaType.TEXT_HTML)
    public String helloHtml(){
        String resource = "<h1>Hello HTML</h1>";
        return resource;
    }*/
    @GET
    @Path("/helloHtml")
    @Produces(MediaType.TEXT_HTML)
    public String helloHtml(@QueryParam("name") String name,@QueryParam("cardNo") String cardNo, @QueryParam("amount") int amount){
        System.out.println("Name:= " + name);
        System.out.println("Amount is:= " + amount);
        String resposne;
        if (amount > 1000){
            System.out.println("amount is greater than 1000");
            resposne = "credit not approved";
        }
        else {
            System.out.println("amount is less than 1000");
            resposne = "credit approved";
        }
        return "<html>" +"<title>" +"Credit Card Authorization " +name+"</title>"+"<body> <h1> "+ resposne +"<p>" +name+
                "</p></h1></body></html>";
    }
}
