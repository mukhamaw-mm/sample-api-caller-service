package com.mukham.sampleapicallerservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "callerapp.api.receiverservice.endpoint")// read values from properties which behind 'app.config' prefix
@Data
public class AppConfig {

    private String requestPathVariableAndResponseObjectDemoUrl;
    private String requestParamStringAndResponseObjectListDemoUrl;
    private String requestFromHeaderAndRequestBodyAndResponseNestedObjectDemoUrl;
    private String requestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemoUrl;
    private String requestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemoUrl;

    private String errorBadRequestResponseDemoUrl;
    private String errorNotFoundResponseDemoUrl;
    private String errorInternalServerErrorResponseDemoUrl;
}
