package com.tooomor.online.store.delivery.warehouse;

import com.tooomor.online.store.delivery.model.GeolocationDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties("warehouse")
public class WarehouseClient {

    private String apiHost;

    private final RestTemplate restTemplate;

    public WarehouseClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public GeolocationDTO getGeolocationByWarehouseId(String warehouseId) {
        return restTemplate.getForObject
                (apiHost + "/warehouse/"  + warehouseId +"/geolocation", GeolocationDTO.class);
    }


    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

}
