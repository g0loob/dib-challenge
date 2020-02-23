package com.example.dibchallenge.punk_api_lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class PunkApiRestTemplateResponseErrorHandler implements ResponseErrorHandler {

    Logger LOG = LoggerFactory.getLogger(PunkApiRestTemplateResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus.Series status = clientHttpResponse.getStatusCode().series();
        return status == HttpStatus.Series.CLIENT_ERROR || status == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            // TODO: handle server error
            LOG.warn("PunkAPI server error!");
        } else if (clientHttpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            if (clientHttpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                // TODO: what to do with this???
                LOG.warn("RestTemplate returned 403!");
            }
        }
    }
}
