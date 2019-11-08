package com.konantech.spring.controller.rest;

import com.konantech.spring.response.RestResponse;
import com.konantech.spring.util.JSONUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Api(tags = {"DEMO"}, produces = "application/json")
public class DemoRestController {

    private static Logger logger = LoggerFactory.getLogger(DemoRestController.class);

    @ApiOperation(value = "요청", notes = "작업 요청", authorizations = {@Authorization(value = "apiKey", scopes = {})})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "port", value = "PORT", required = true, dataType = "String", paramType = "path", defaultValue = "2019")
    })
    @RequestMapping(value = "/v1/{port}/job", method = RequestMethod.POST)
    public Object jobRequest(@PathVariable int port, HttpServletRequest request) throws Exception {
        RestResponse response = new RestResponse();
        Map resultMap = JSONUtil.objectToMap(response);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "상태", notes = "서버 상태", authorizations = {@Authorization(value = "apiKey", scopes = {})})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "port", value = "PORT", required = true, dataType = "String", paramType = "path", defaultValue = "2019")
    })
    @RequestMapping(value = "/v1/{port}/status", method = RequestMethod.GET)
    public Object serverStatus(@PathVariable int port, HttpServletRequest request) throws Exception {
        RestResponse response = new RestResponse();
        Map resultMap = JSONUtil.objectToMap(response);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
