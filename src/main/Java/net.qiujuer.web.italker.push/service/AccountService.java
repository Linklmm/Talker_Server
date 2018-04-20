package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.db.User;

import javax.validation.constraints.Past;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 *
 * */
@Path("/account")
public class AccountService {
    @GET
    @Path("/login")
    public String get(){
        return "you get the login";
    }
    @Past
    @Path("/login")
    //指定请求与返回的相应体为Json
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User Post(){
        User user=new User();
        user.setName("美女");
        user.setSex(2);
        return user;
    }

}
