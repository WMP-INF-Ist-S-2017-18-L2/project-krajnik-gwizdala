package pik.clinic.clinicproject.View;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the scheduler-test.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("scheduler-test")
@HtmlImport("scheduler-test.html")
public class SchedulerTest extends PolymerTemplate<SchedulerTest.SchedulerTestModel> {

    /**
     * Creates a new SchedulerTest.
     */
    public SchedulerTest() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between SchedulerTest and scheduler-test.html
     */
    public interface SchedulerTestModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
