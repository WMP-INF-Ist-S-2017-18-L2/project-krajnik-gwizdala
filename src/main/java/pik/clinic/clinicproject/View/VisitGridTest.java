package pik.clinic.clinicproject.View;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import pik.clinic.clinicproject.Model.Visit;
import pik.clinic.clinicproject.Repositories.VisitRepository;

import javax.annotation.PostConstruct;

/**
 * A Designer generated component for the visit-grid-test.html template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("visittest")
@Tag("visit-grid-test")
@HtmlImport("visit-grid-test.html")
public class VisitGridTest extends PolymerTemplate<VisitGridTest.VisitGridTestModel> {

    Visit currentSelectedVisit;

    @Autowired
    VisitRepository visitRepository;

    TemplateRenderer<Visit> renderer = TemplateRenderer.<Visit>of("");


    @Id("visitGrid")
    private Grid visitGrid;
    @Id("rentButton")
    private Button rentButton;

    /**
     * Creates a new VisitGridTest.
     */
    public VisitGridTest() {
        // You can initialise any data required for the connected UI components here.
        /*visitGrid.addSelectionListener(item -> {
            item.getFirstSelectedItem().ifPresent(selectedItem -> {
                currentSelectedVisit = (Visit) selectedItem;
                if (currentSelectedVisit != null) {
                    rentButton.setEnabled(true);
                } else {
                    rentButton.setVisible(false);
                }
                rentButton.addClickListener(buttonClickEvent -> {

                    Dialog dialog = new Dialog();
                    dialog.add(new Label("Rozszerzone informacje o wizycie: "));

                    dialog.setWidth("400px");
                    dialog.setHeight("150px");
                    dialog.open();
                });

            });
        });*/


    }

    @PostConstruct
    public void test() {
        CurrentPatient currentPatient = new CurrentPatient();
        visitGrid.setItems(visitRepository.findAll());
        visitGrid.addColumn(renderer.withProperty("id", Visit::getId));
        visitGrid.addColumn(renderer.withProperty("opis", Visit::getSummary));
        visitGrid.addColumn(renderer.withProperty("data", Visit::getDateOfVisit));
        visitGrid.addColumn(renderer.withProperty("lekarz", Visit::getDoctor));
        visitGrid.addColumn(renderer.withProperty("pacjent", Visit::getPatient));
    }

    /**
     * This model binds properties between VisitGridTest and visit-grid-test.html
     */
    public interface VisitGridTestModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
