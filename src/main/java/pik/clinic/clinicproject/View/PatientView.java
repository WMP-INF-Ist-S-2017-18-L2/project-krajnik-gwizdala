package pik.clinic.clinicproject.View;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.*;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import pik.clinic.clinicproject.backend.security.SecurityUtils;


/**
 * A Designer generated component for the patient-view.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */

@Route(value = "patient-view", layout = MainView.class)
@PageTitle("MediClinic - Panel Pacjenta")
@Tag("patient-view")
@HtmlImport("views/patient-view.html")
public class PatientView extends PolymerTemplate<PatientView.PatientViewModel>  {


    @Id("logout")
    private Button logout;

    /**
     * Creates a new PatientView.
     */
    public PatientView()  {


        if(SecurityUtils.isUserLoggedIn())
        {

                logout.addClickListener(buttonClickEvent -> {
                    UI.getCurrent().getPage().executeJavaScript("location.assign('logout')");
                });

        }

    }


    /**
     * This model binds properties between PatientView and patient-view.html
     */
    public interface PatientViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
