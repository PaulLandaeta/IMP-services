package com.mojix.web.services;


import com.google.gson.Gson;

import com.mojix.model.thing.Things;
import com.mojix.properties.PropertiesController;
import com.mojix.restClient.RestClientPatch;
import com.mojix.restClient.RestClientPut;
import com.mojix.services.PipesService;
import com.mojix.services.ThingsService;
import com.mojix.utils.DateUtil;
import com.mojix.web.models.RacksModel;
import com.mojix.web.utilities.RestUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang.time.DateUtils;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/Inventory")
@Api("/Inventory")
public class Inventory {
    private PropertiesController propertiesController = new PropertiesController();
    private final Logger log = LoggerFactory.getLogger(Inventory.class);

    @POST
    @Path("/Inventory")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(position = 0, value = "Inventory")
    public Response getInventory(RacksModel racksModel) throws IOException, ParseException {

        DateUtil dateUtil;
        if (racksModel.getStartDate() == null || racksModel.getStartDate().equals("string"))
            dateUtil = new DateUtil();
        else
            dateUtil = new DateUtil(racksModel.getStartDate());

        if (racksModel.getGroup() == null || racksModel.getGroup().equals("string"))
            racksModel.setGroup(propertiesController.getGroup());
        if (dateUtil.isValidDate()) {

            Date startDate = dateUtil.getStartDate();
            Date endDate = DateUtils.addDays(startDate, 1);

            PipesService pipesService = new PipesService();
            Map<String, Object> newInventory = pipesService.getResults(startDate, endDate, racksModel.getGroup(), dateUtil.getNameDate());

            ThingsService thingsService = new ThingsService();
            Things things = thingsService.execute(dateUtil.getSerialDate());

            Gson gson = new Gson();

            String json;

            if (things.getTotal() > 0) {
                try {
                    int id = things.getResults().get(0).getId();

                    Map<String, Object> resultUpdate = new HashMap<>();
                    resultUpdate.put("group", newInventory.get("group"));
                    resultUpdate.put("name", newInventory.get("name"));
                    resultUpdate.put("serialNumber", newInventory.get("serialNumber"));
                    resultUpdate.put("thingTypeCode", propertiesController.getThingTypeCode());
                    resultUpdate.put("udfs", newInventory.get("udfs"));
                    newInventory.put("period", dateUtil.getNameDate());
                    String url = propertiesController.getEndPointThings() + id + propertiesController.updateparamsThing();
                    json = gson.toJson(resultUpdate);
                    RestClientPatch restClientPatch = new RestClientPatch(url, json);
                    restClientPatch.execute();
                    log.info("{} The thing has Updated", new Date());
                    return RestUtils.sendOkResponse("The thing has updated");
                } catch (Exception e) {
                    log.info("{} Failed to Update the thing", new Date());
                    log.error("Error {}", e);
                    return RestUtils.sendBadResponse(e.toString());
                }
            } else {
                try {
                    json = gson.toJson(newInventory);
                    RestClientPut restClientPut = new RestClientPut(propertiesController.putEndPointThing(), json);
                    restClientPut.execute();
                    log.info("{} The thing has created", new Date());
                    return RestUtils.sendOkResponse("The thing has created");
                } catch (Exception e) {
                    log.info("{} Failed to create the thing", new Date());
                    log.error("Error {}", e);
                    return RestUtils.sendBadResponse(e.toString());
                }
            }
        } else {
            return RestUtils.sendBadResponse("Invalid Date");
        }
    }
}