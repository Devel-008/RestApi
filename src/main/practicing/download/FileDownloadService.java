package demo.rest.api.download;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.io.File;

@Path("/files")
public class FileDownloadService {
    private static final String FILE_PATH = "/Users/ishasethia/IdeaProjects/RestAPI/demorest/myFile.txt";
    @GET
    @Path("/txt")
    @Produces("text/plain")
    public Response getFile() {
        File file = new File(FILE_PATH);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition","attachment; filename=\"javatpoint_file.txt\"");
        return response.build();

    }
}
