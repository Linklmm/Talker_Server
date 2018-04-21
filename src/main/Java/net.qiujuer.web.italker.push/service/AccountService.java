package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.api.account.RegisterModel;
import net.qiujuer.web.italker.push.bean.db.User;

import javax.validation.constraints.Past;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 *
 * */
@Path("/account")
public class AccountService {

    @POST
    @Path("/register")
    //指定请求与返回的相应体为Json
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User register(RegisterModel model){
        User user=new User();
        user.setName(model.getName());
        user.setSex(2);
        return user;
    }

}
