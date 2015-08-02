package com.loopme.testtask;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loopme.testtask.dao.CreativesDao;
import com.loopme.testtask.pojo.AdRequest;
import com.loopme.testtask.pojo.AdResponse;
import com.loopme.testtask.pojo.Creative;

@Component
@Path("/creatives")
public class CreativesResource {

    @Autowired
    private CreativesDao creativesDao;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AdResponse echo(AdRequest request) {
        List<Creative> creatives = creativesDao.getCreatives(request);
        AdResponse response = new AdResponse();
        response.setAds(creatives);
        return response;
    }

}
