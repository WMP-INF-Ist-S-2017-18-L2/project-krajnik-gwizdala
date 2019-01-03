package pik.clinic.clinicproject.View;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the doctor-view.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("doctor-view")
@Tag("doctor-view")
@HtmlImport("doctor-view.html")
public class DoctorView extends PolymerTemplate<DoctorView.DoctorViewModel> {

    /**
     * Creates a new DoctorView.
     */
    public DoctorView() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between DoctorView and doctor-view.html
     */
    public interface DoctorViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
