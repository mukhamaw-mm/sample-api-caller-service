package com.mukham.sampleapicallerservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mukham.sampleapicallerservice.config.AppConfig;
import com.mukham.sampleapicallerservice.model.request.Student;
import com.mukham.sampleapicallerservice.model.response.CallerServiceResponse;
import com.mukham.sampleapicallerservice.model.response.ResponseObject;
import com.mukham.sampleapicallerservice.model.response.ResponseObjectList;
import com.mukham.sampleapicallerservice.service.CallerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Slf4j // for logging by using lombok
@Service // create bean class
public class CallerServiceImpl implements CallerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppConfig appConfig;

    @Override
    public CallerServiceResponse callRequestPathVariableAndResponseObjectDemo() {
        CallerServiceResponse callerResponse;

        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String actionType = "bill payment"; // prepare for path variable as api request, this is demo and so, I used hard coded value.

            // you can also use map instead of integer id for adding multiple path variables.

            log.info("API Calling info, url: {}, request data: {}", appConfig.getRequestPathVariableAndResponseObjectDemoUrl(), actionType);

            // add url which you want to call, add Response Type from third party and add require parameters in restTemplate getForEntity method
            // path variable will auto add after we put parameters
            // catch response object with pojo class as api response fields
            ResponseEntity<Student> response = restTemplate.getForEntity(appConfig.getRequestPathVariableAndResponseObjectDemoUrl(), Student.class, actionType);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            if(response.getStatusCode()==HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestPathVariableAndResponseObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                /**
                 * Here, you can do your logic for error handling if thirdparty api calling was something wrong.
                 * You can check api error response by checking http status code like this if-else condition.
                 * eg. saving error code, message into db or return customize error response message to frontend or other service or etc.
                 * eg. you can also call another api if first api was error, like that, depend on your application logic.
                 * but, here, I don't do any logic as this is demo project and so, I did directly return that error message.
                 */
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestPathVariableAndResponseObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;


    }

    @Override
    public CallerServiceResponse callRequestParamStringAndResponseObjectListDemo() {


        CallerServiceResponse callerResponse;
        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String nameValue = "Mu Kham";
            String phone = "+959123456789";
            // prepare url with param value
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getRequestParamStringAndResponseObjectListDemoUrl())
                    .queryParam("name",nameValue)
                    .queryParam("phone",phone)
                    .build();

            String url = urlBuilder.toUriString();

            log.info("API Calling info, url: {}, request data: {}", url, null);

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate getForEntity method
            // receiver application requested as PUT method, so, I used restTemplate exchange method instead of getForEntity method
            // param values is already included in url variable
            // catch response object with pojo class as api response fields
            // response is object list, so, I did catch response object as list [] in response
            ResponseEntity<Student []> response = restTemplate.getForEntity(url, Student [].class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see com.mukham.sampleapicallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestParamStringAndResponseObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestParamStringAndResponseObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }



    @Override
    public CallerServiceResponse callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo() {

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request

            // sample hard coded token
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluQGdtYWlsLmNvbSIsInJvbGVzIjpbIkFETUlOIiwiTUFOQUdFUiIsIk5PUk1BTF9VU0VSIiwiU1VQRVJfQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3NwcmluZy1zZWN1cml0eS1qd3QvbG9naW4iLCJleHAiOjE2NjMwMTczMDl9.qWnl1aA63p2XXchc6P4Kv2yS4ghVCnEdwB0-06-0qXU";
            String authorizationHeaderValue = "Bearer "+token; // add Bearer in front of token.

            headers.set("Authorization", authorizationHeaderValue); // add Bearer token as Authorization in header.

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */
            Student student = new Student();
            student.setName("Mu Kham Aw");
            student.setPhone("+959123456789");
            student.setAddress("Yangon");
            student.setClassName("Room A");

            // build request data and headers into HttpEntity.
            HttpEntity<Student> httpEntity = new HttpEntity<>(student, headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", appConfig.getRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemoUrl(), httpEntity.getBody(), httpEntity.getHeaders());

            // postForEntity is used when you want to call Http POST method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.POST, httpEntity, String.class);
            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            ResponseEntity<ResponseObject> response = restTemplate.postForEntity(appConfig.getRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemoUrl(), httpEntity, ResponseObject.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see com.mukham.sampleapicallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo() {
        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request

            // sample hard coded token
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluQGdtYWlsLmNvbSIsInJvbGVzIjpbIkFETUlOIiwiTUFOQUdFUiIsIk5PUk1BTF9VU0VSIiwiU1VQRVJfQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3NwcmluZy1zZWN1cml0eS1qd3QvbG9naW4iLCJleHAiOjE2NjMwMTczMDl9.qWnl1aA63p2XXchc6P4Kv2yS4ghVCnEdwB0-06-0qXU";
            String authorizationHeaderValue = "Bearer "+token; // add Bearer in front of token.

            headers.set("Authorization", authorizationHeaderValue); // add Bearer token as Authorization in header.

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String nameValue = "Mu Kham Aw";
            String phone = "+959123456789";
            // prepare url with param value
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemoUrl())
                    .queryParam("name",nameValue)
                    .queryParam("phone",phone)
                    .build();

            String url = urlBuilder.toUriString();


            // build request data and headers into HttpEntity.
            // Receiver API (server/other service/ThirdParty api) don't request body data. So, I don't put that in httpEntity.
            HttpEntity httpEntity = new HttpEntity<>(headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", url, null, httpEntity.getHeaders());

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate exchange method
            // here, we can't use getForEntity as we need to add headers, So, I used exchange method.
            // catch response object with pojo class as api response fields
            ResponseEntity<ResponseObjectList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ResponseObjectList.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see com.mukham.sampleapicallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo() {
        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request

            // sample hard coded token
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluQGdtYWlsLmNvbSIsInJvbGVzIjpbIkFETUlOIiwiTUFOQUdFUiIsIk5PUk1BTF9VU0VSIiwiU1VQRVJfQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3NwcmluZy1zZWN1cml0eS1qd3QvbG9naW4iLCJleHAiOjE2NjMwMTczMDl9.qWnl1aA63p2XXchc6P4Kv2yS4ghVCnEdwB0-06-0qXU";
            String authorizationHeaderValue = "Bearer "+token; // add Bearer in front of token.

            headers.set("Authorization", authorizationHeaderValue); // add Bearer token as Authorization in header.

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            Map<String, String> pathVarsMap = new HashMap<>();
            pathVarsMap.put("accName","MU KHAM AW"); // hard coded values, real values will get from somewhere else in real world project
            pathVarsMap.put("card","VISA");

            // hard coded values, real values will get from somewhere else in real world project
            int pageNumber = 0;
            int pageSize = 10;
            String sortBy = "id";
            String actionType = "Bill";
            // prepare url with param value
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemoUrl())
                    .queryParam("pageNo",pageNumber)
                    .queryParam("pageSize",pageSize)
                    .queryParam("sortBy",sortBy)
                    .queryParam("actionType",actionType)
                    .build();

            String url = urlBuilder.toUriString();
            // final url will be ->
            // http://localhost:8081/receiver-service/api/requestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo/accountName/{accName}/cardType/{card}?pageNo=0&pageSize=10&sortBy=Id&actionType=Bill

            // build request data and headers into HttpEntity.
            // Receiver API (server/other service/ThirdParty api) don't request body data. So, I don't put that in httpEntity.
            HttpEntity httpEntity = new HttpEntity<>(headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", url, pathVarsMap, httpEntity.getHeaders());

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate exchange method
            // here, we can't use getForEntity as we need to add headers, So, I used exchange method.
            // catch response object with pojo class as api response fields
            ResponseEntity<ResponseObjectList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ResponseObjectList.class, pathVarsMap);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see com.mukham.sampleapicallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callErrorBadRequestResponseDemo() {
        CallerServiceResponse callerResponse;
        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String nameValue = "Mu Kham Aw";
            String phoneValue = "+959123456789";
            // prepare url with param value like -> http://localhost:8081/receiver-service/api/requestMultiParamsDemo?id=1&name=Mr.Ye Win
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getErrorBadRequestResponseDemoUrl())
                    .queryParam("name",nameValue)
                    .queryParam("phone",phoneValue)
                    .build();

            String url = urlBuilder.toUriString(); // it will -> http://localhost:8081/receiver-service/api/errorBadRequestResponseDemo?name=Ye Win&phone=+959123456789

            log.info("API Calling info, url: {}, request data: {}", url, null);

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate getForEntity method
            // param values is already included in url variable
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see com.mukham.sampleapicallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ErrorBadRequestResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                /**
                 * Here, you can do your logic for error handling if thirdparty api calling was something wrong.
                 * You can check api error response by checking http status code like this if-else condition.
                 * eg. saving error code, message into db or return customize error response message to frontend or other service or etc.
                 * eg. you can also call another api if first api was error, like that, depend on your application logic.
                 * but, here, I don't do any logic as this is demo project and so, I did directly return that error message.
                 */
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ErrorBadRequestResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                ObjectMapper objectMapper = new ObjectMapper();
                // here we can predict api response by calling via Postman first and catch with same field java pojo class to convert json string response to object by using objectMapper.
                ResponseObjectList responseObject = objectMapper.readValue(response.getBody(), ResponseObjectList.class);
                callerResponse.setThirdPartyResponseData(responseObject);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callErrorNotFoundResponseDemo() {
        CallerServiceResponse callerResponse;
        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            // prepare for multi path variable by using Map as api request,
            // this is demo and so, I used hard coded values.
            Map<String, Long> pathVarsMap = new HashMap<>();
            pathVarsMap.put("id",1L);
            // api request link is like localhost:8081/receiver-service/api/errorNotFoundResponseDemo/{id}

            log.info("API Calling info, url: {}, request data: {}", appConfig.getErrorNotFoundResponseDemoUrl(), pathVarsMap);

            // add url which you want to call, add Response Type from third party and add require parameters in restTemplate getForEntity method
            // path variable will auto add after we put parameters by using map
            ResponseEntity<String> response = restTemplate.getForEntity(appConfig.getErrorNotFoundResponseDemoUrl(), String.class, pathVarsMap);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.


            /**
             * @see com.mukham.sampleapicallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ErrorNotFoundResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                /**
                 * Here, you can do your logic for error handling if thirdparty api calling was something wrong.
                 * You can check api error response by checking http status code like this if-else condition.
                 * eg. saving error code, message into db or return customize error response message to frontend or other service or etc.
                 * eg. you can also call another api if first api was error, like that, depend on your application logic.
                 * but, here, I don't do any logic as this is demo project and so, I did directly return that error message.
                 */
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ErrorNotFoundResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                ObjectMapper objectMapper = new ObjectMapper();
                // here we can predict api response by calling via Postman first and catch with same field java pojo class to convert json string response to object by using objectMapper.
                ResponseObjectList responseObject = objectMapper.readValue(response.getBody(), ResponseObjectList.class);
                callerResponse.setThirdPartyResponseData(responseObject);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callErrorInternalServerErrorResponseDemo() {

        CallerServiceResponse callerResponse;
        try {
            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request
//          headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // for form urlencoded request


            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */
            Student student = new Student();
            student.setName("Mu Kham Aw");
            student.setPhone("+959123456789");
            student.setAddress("Yangon");
            student.setClassName("Room A");

            HttpEntity<Student> httpEntity = new HttpEntity<>(student, headers);

            // you can use URI type instead of url string in restTemplate getForEntity, postForEntity methods.
//            URI url = new URI("http://localhost:8081/receiver-service/api/requestBodyObjectDemo");

            log.info("API Calling info, url: {}, request data: {}, headers: {}", appConfig.getErrorInternalServerErrorResponseDemoUrl(), httpEntity.getBody(), httpEntity.getHeaders());


            // postForEntity is used when you want to call Http POST method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.POST, httpEntity, String.class);

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            ResponseEntity<String> response = restTemplate.postForEntity(appConfig.getErrorInternalServerErrorResponseDemoUrl(), httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see com.mukham.sampleapicallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            if(response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
                callerResponse = new CallerServiceResponse(); // create new object and you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ErrorInternalServerErrorResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                /**
                 * Here, you can do your logic for error handling if thirdparty api calling was something wrong.
                 * You can check api error response by checking http status code like this if-else condition.
                 * eg. saving error code, message into db or return customize error response message to frontend or other service or etc.
                 * eg. you can also call another api if first api was error, like that, depend on your application logic.
                 * but, here, I don't do any logic as this is demo project and so, I did directly return that error message.
                 */
                callerResponse = new CallerServiceResponse(); // create new object and you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ErrorInternalServerErrorResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }
        return callerResponse;
    }


    
}
