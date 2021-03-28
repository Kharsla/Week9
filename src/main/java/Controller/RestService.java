package Controller;

import Entity.User;
import Persistence.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Path("/users")
public class RestService {
        // The Java method will process HTTP GET requests
        @GET
        // The Java method will produce content identified by the MIME Media type "text/plain"
        @Produces("text/plain")
        public Response getUser(@QueryParam("id") String userId) {
           UserDao dao = new UserDao();
            List<User> users = null;
            //https://stackoverflow.com/questions/15332733/how-to-convert-list-data-into-json-in-java
            StringBuilder sb = new StringBuilder();

            if(userId == null) {
                 users = dao.getAllUsers();
                int i = 0;
                while (i < users.size() - 1) {
                    sb.append(users.get(i));
                    sb.append(" ");
                    i++;
                }
                sb.append(users.get(i));
            } else {
                int id = Integer.parseInt(userId);
                users = dao.getUsersById(id);
                if (users.size() > 0) {
                    sb.append(users.get(0));
                }

            }



            String usersOutput = sb.toString();
            return Response.status(200).entity(usersOutput).build();
        }
    }

