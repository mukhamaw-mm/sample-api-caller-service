package com.mukham.sampleapicallerservice.service;

import com.mukham.sampleapicallerservice.model.response.CallerServiceResponse;

public interface CallerService {

    /** to call request and response demo apis **/
    CallerServiceResponse callRequestPathVariableAndResponseObjectDemo();
    CallerServiceResponse callRequestParamStringAndResponseObjectListDemo();
    CallerServiceResponse callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo();
    CallerServiceResponse callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo();
    CallerServiceResponse callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo();


    /** to call error demo apis **/
    CallerServiceResponse callErrorBadRequestResponseDemo();
    CallerServiceResponse callErrorNotFoundResponseDemo();
    CallerServiceResponse callErrorInternalServerErrorResponseDemo();
}
