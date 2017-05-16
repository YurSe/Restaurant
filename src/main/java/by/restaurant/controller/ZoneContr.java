package by.restaurant.controller;

import by.restaurant.model.Zone;
import by.restaurant.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pavel on 02.05.2017.
 */
@Controller
@Scope("session")
public class ZoneContr {

    @Autowired
    private SpringUserController springUserController;

    @Autowired
    private ZoneService zoneService;

    private Zone zone = new Zone();

    public void redirectToPageWithTableReservation() throws IOException {
        if (springUserController.IsAuthorized()) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/pages/reservation.xhtml?faces-redirect=true");
        } else {
            showAuthorizationNotification();
        }
    }

    private void showAuthorizationNotification() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("messageAuth", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Warning", "For reserve a table you need to be authorized."));
    }

    public void deleteZone(Long id) {
        zoneService.delete(id);
    }

    public void initZone() {
        zone = new Zone();
    }

    public List<Zone> getZones() {
        return zoneService.getAll();
    }

    public void saveZone() {
        zoneService.save(zone);
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
