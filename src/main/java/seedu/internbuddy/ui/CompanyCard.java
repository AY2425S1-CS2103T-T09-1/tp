package seedu.internbuddy.ui;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.internbuddy.model.application.Application;
import seedu.internbuddy.model.company.Company;

/**
 * An UI component that displays information of a {@code company}.
 */
public class CompanyCard extends UiPart<Region> {

    private static final String FXML = "CompanyListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Company company;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label application;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code companyCode} with the given {@code company} and index to display.
     */
    public CompanyCard(Company company, int displayedIndex) {
        super(FXML);
        this.company = company;
        id.setText(displayedIndex + ". ");
        name.setText(company.getName().fullName);
        email.setText(company.getEmail().value);

        /* phone number and address are optional */
        setOptionals();

        List<Application> applications = company.getApplications();
        application.setText(applications.isEmpty()
                ? "Applications: CLOSED"
                : "Applications: " + IntStream.range(0, applications.size())
                    .mapToObj(i -> (i + 1) + ". " + applications.get(i))
                    .collect(Collectors.joining(", ")));

        company.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void setOptionals() {
        // to be changed when optional classes get implemented
        boolean hasPhoneNumber = !company.getPhone().value.equals("000");
        boolean hasAddress = !company.getAddress().value.equals("No Address");

        if (hasPhoneNumber) {
            phone.setText(company.getPhone().value);
        } else {
            phone.setManaged(false);
            phone.setVisible(false);
        }
        if (hasAddress) {
            address.setText(company.getAddress().value);
        } else {
            address.setManaged(false);
            address.setVisible(false);
        }
    }
}
