package ui.windows;

import backend.services.StatsService;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import ui.UIController;

import java.util.List;

public class AddPlayerWindow extends BasicWindow {

    private final UIController ui;
    private final StatsService service;

    public AddPlayerWindow(UIController ui, StatsService service) {
        super("Add New Player");
        this.ui = ui;
        this.service = service;
        setHints(List.of(Hint.CENTERED));
        setComponent(build());
    }

    private Component build() {
        Panel panel = new Panel();
        panel.setLayoutManager(
                new LinearLayout(Direction.VERTICAL)
        );

        panel.addComponent(new Label("First Name:"));
        TextBox firstNameBox = new TextBox();
        panel.addComponent(firstNameBox);

        panel.addComponent(new Label("Last Name:"));
        TextBox lastNameBox = new TextBox();
        panel.addComponent(lastNameBox);

        panel.addComponent(new Label("Height:"));
        TextBox heightBox = new TextBox();
        panel.addComponent(heightBox);

        panel.addComponent(new Label("Weight:"));
        TextBox weightBox = new TextBox();
        panel.addComponent(weightBox);

        panel.addComponent(new Label("Jersey Number:"));
        TextBox jerseyBox = new TextBox();
        panel.addComponent(jerseyBox);

        panel.addComponent(new Label("Position:"));
        TextBox positionBox = new TextBox();
        panel.addComponent(positionBox);

        panel.addComponent(new Label("Team ID:"));
        TextBox teamIdBox = new TextBox();
        panel.addComponent(teamIdBox);

        panel.addComponent(new EmptySpace());

        panel.addComponent(new Button("Submit", () -> {

            String firstName = firstNameBox.getText();
            String lastName = lastNameBox.getText();
            String heightText = heightBox.getText();
            String weightText = weightBox.getText();
            String jerseyText = jerseyBox.getText();
            String position = positionBox.getText();
            String teamIdText = teamIdBox.getText();

            if (firstName.isBlank() || lastName.isBlank() || heightText.isBlank() || weightText.isBlank() || jerseyText.isBlank() || position.isBlank() || teamIdText.isBlank()) {
                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Error",
                        "Fill out all of the information.",
                        MessageDialogButton.OK
                );
                return;
            }

            try {
                int jersey = Integer.parseInt(jerseyText);
                int height = Integer.parseInt(heightText);
                int weight = Integer.parseInt(weightText);
                int teamId = Integer.parseInt(teamIdText);

                service.addPlayer(firstName, lastName, height, weight, jersey, position, teamId);

                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Success",
                        "Player added successfully!",
                        MessageDialogButton.OK
                );

                ui.closeWindow(this);

            } catch (NumberFormatException e) {
                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Error",
                        "Jersey number and Team ID must be numbers.",
                        MessageDialogButton.OK
                );
            }
        }));

        panel.addComponent(new Button("Back", () -> ui.closeWindow(this)));

        return panel;
    }
}