package pik.clinic.clinicproject.View;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the grid-select-test-view.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("grid-select-test-view")
@HtmlImport("grid-select-test-view.html")
public class GridSelectTestView extends PolymerTemplate<GridSelectTestView.GridSelectTestViewModel> {

    /**
     * Creates a new GridSelectTestView.
     */
    public GridSelectTestView() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between GridSelectTestView and grid-select-test-view.html
     */
    public interface GridSelectTestViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
