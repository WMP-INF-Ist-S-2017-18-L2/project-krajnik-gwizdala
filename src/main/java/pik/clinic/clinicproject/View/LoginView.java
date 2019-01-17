package pik.clinic.clinicproject.View;

import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the login-view.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route("login")
@Tag("login-view")
@HtmlImport("login-view.html")
public class LoginView extends PolymerTemplate<LoginView.LoginViewModel> implements PageConfigurator, AfterNavigationObserver {

    /**
     * Creates a new LoginView.
     */
    public LoginView() {
        // You can initialise any data required for the connected UI components here.
    }
    @Override
    public void configurePage(InitialPageSettings settings) {
        // Force login page to use Shady DOM to avoid problems with browsers and
        // password managers not supporting shadow DOM
        settings.addInlineWithContents(InitialPageSettings.Position.PREPEND,
                "window.customElements=window.customElements||{};" +
                        "window.customElements.forcePolyfill=true;" +
                        "window.ShadyDOM={force:true};", InitialPageSettings.WrapMode.JAVASCRIPT);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        boolean error = event.getLocation().getQueryParameters().getParameters().containsKey("error");

    }
    /**
     * This model binds properties between LoginView and login-view.html
     */
    public interface LoginViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
        void setError(boolean error);
    }
}
