package com.hcode.imp.web.services;


import com.google.gson.Gson;

import com.hcode.imp.utils.RestUtils;
import com.hcode.imp.web.models.RacksModel;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;


import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/Inventory")
@Api("/Inventory")
public class Inventory {

    private final Logger log = LoggerFactory.getLogger(Inventory.class);

    @POST
    @Path("/Inventory")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(position = 0, value = "Inventory")
    public Response getInventory(RacksModel racksModel) throws IOException, ParseException {
            return RestUtils.sendBadResponse("Invalid Date");
    }
}