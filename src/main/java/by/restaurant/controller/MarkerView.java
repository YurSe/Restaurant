package by.restaurant.controller;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
public class MarkerView implements Serializable {

    private MapModel simpleModel;

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();

        LatLng coord = new LatLng(53.684562, 23.840140);

        simpleModel.addOverlay(new Marker(coord, "Restaurant"));
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
}