package pik.clinic.clinicproject.View;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the patient-view.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("patient-view")
@Tag("patient-view")
@HtmlImport("patient-view.html")
public class PatientView extends PolymerTemplate<PatientView.PatientViewModel> {

    /**
     * Creates a new PatientView.
     */
    public PatientView() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between PatientView and patient-view.html
     */
    public interface PatientViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
