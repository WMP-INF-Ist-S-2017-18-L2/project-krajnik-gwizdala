package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import pik.clinic.clinicproject.Model.Patient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * A Designer generated component for the patient-view.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("patient-view")
@Tag("patient-view")
@HtmlImport("patient-view.html")
public class PatientView extends PolymerTemplate<PatientView.PatientViewModel>   {

    @Id("nameLabel")
    private Label nameLabel;
    @Id("peselLabel")
    private Label peselLabel;
    @Id("addresLabel")
    private Label addresLabel;
    @Id("logout")
    private Button logout;

    /**
     * Creates a new PatientView.
     */
    public PatientView(HttpServletRequest request) throws Exception{
        // You can initialise any data required for the connected UI components here.
        HttpSession session = request.getSession(false);



            Patient p = (Patient) request.getSession().getAttribute("patient");
            nameLabel.setText(p.getFirstName());
            peselLabel.setText(String.valueOf(p.getPesel()));
            addresLabel.setText(p.getAddress());
            logout.addClickListener(event -> {

                session.invalidate();
                getUI().ifPresent(ui -> ui.navigate("login"));


            });






    }
//

    /**
     * This model binds properties between PatientView and patient-view.html
     */
    public interface PatientViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
