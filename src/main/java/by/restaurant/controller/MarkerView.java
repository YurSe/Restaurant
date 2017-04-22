package by.restaurant.controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Properties;

@Controller
@Scope("singleton")
public class MarkerView implements Serializable {

    private MapModel simpleModel;

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();

        Properties property = new Properties();

        LatLng coordinates = new LatLng(Integer.parseInt(property.getProperty("lat")),
                Integer.parseInt(property.getProperty("lng")));

        simpleModel.addOverlay(new Marker(coordinates, "Restaurant"));
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
}