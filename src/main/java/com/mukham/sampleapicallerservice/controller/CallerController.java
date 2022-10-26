package com.mukham.sampleapicallerservice.controller;

import com.mukham.sampleapicallerservice.model.response.CallerServiceResponse;
import com.mukham.sampleapicallerservice.service.CallerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // for rest endpoint
@Slf4j // for logging by using lombok
@RequestMapping("/caller")
public class CallerController {
    @Autowired
    CallerService callerService;

    @GetMapping("/callRequestPathVariableAndResponseObjectDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestPathVariableAndResponseObjectDemo(){

        try{
            log.info("Enter callRequestPathVariableAndResponseObjectDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestPathVariableAndResponseObjectDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestPathVariableAndResponseObjectDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/callRequestParamStringAndResponseObjectListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestParamStringAndResponseObjectListDemo(){

        try{
            log.info("Enter callRequestParamStringAndResponseObjectListDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestParamStringAndResponseObjectListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestParamStringAndResponseObjectListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo(){

        try{
            log.info("Enter callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo(){

        try{
            log.info("Enter callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo(){

        try{
            log.info("Enter callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/callErrorBadRequestResponseDemoAPI")
    public ResponseEntity<CallerServiceResponse> callErrorBadRequestResponseDemo(){

        try{
            log.info("Enter callErrorBadRequestResponseDemo method");

            CallerServiceResponse callerResponse = callerService.callErrorBadRequestResponseDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callErrorBadRequestResponseDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/callErrorNotFoundResponseDemoAPI")
    public ResponseEntity<CallerServiceResponse> callErrorNotFoundResponseDemo(){

        try{
            log.info("Enter callErrorNotFoundResponseDemo method");

            CallerServiceResponse callerResponse = callerService.callErrorNotFoundResponseDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callErrorNotFoundResponseDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/callErrorInternalServerErrorResponseDemoAPI")
    public ResponseEntity<CallerServiceResponse> callErrorInternalServerErrorResponseDemo(){

        try{
            log.info("Enter callErrorInternalServerErrorResponseDemo method");

            CallerServiceResponse callerResponse = callerService.callErrorInternalServerErrorResponseDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callErrorInternalServerErrorResponseDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
