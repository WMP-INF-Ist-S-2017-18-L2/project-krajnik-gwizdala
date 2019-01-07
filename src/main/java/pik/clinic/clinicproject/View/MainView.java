package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.server.PWA;
import pik.clinic.clinicproject.backend.security.SecurityUtils;

@PWA(name = "Przychodnia MediClinic",shortName = "MediClinic", startPath = "login")
public class MainView extends AbstractAppRouterLayout {
    @Override
    protected void configure(AppLayout appLayout, AppLayoutMenu menu) {

    }
    private void setMenuItem(AppLayoutMenu menu, AppLayoutMenuItem menuItem) {
        menuItem.getElement().setAttribute("theme", "icon-on-top");
        menu.addMenuItem(menuItem);
    }
}
