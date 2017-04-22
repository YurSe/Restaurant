package by.restaurant.controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;


@Controller
@Scope("singleton")
@PropertySource("classpath:gmap.properties")
public class MarkerView implements Serializable {

    @Autowired
    private Environment env;

    private MapModel simpleModel;

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
        LatLng coordinates = new LatLng(Double.parseDouble(env.getProperty("lat")),
                Double.parseDouble(env.getProperty("lng")));

        simpleModel.addOverlay(new Marker(coordinates, "Restaurant"));
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

}