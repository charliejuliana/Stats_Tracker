package ui.windows;

import backend.services.StatsService;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import models.Player;
import ui.UIController;

import java.util.List;

public class UpdatePlayerWindow extends BasicWindow {

    private final UIController ui;
    private final StatsService service;
    private final Player player;

    public UpdatePlayerWindow(UIController ui, StatsService service, Player player) {
        super("Update Player");
        this.ui = ui;
        this.service = service;
        this.player = player;
        setHints(List.of(Hint.CENTERED));
        setComponent(build());
    }

    private Component build() {
        Panel panel = new Panel();
        panel.setLayoutManager(
                new LinearLayout(Direction.VERTICAL)
        );

        panel.addComponent(new Label("First Name:"));
        TextBox firstNameBox = new TextBox(player.firstName());
        panel.addComponent(firstNameBox);

        panel.addComponent(new Label("Last Name:"));
        TextBox lastNameBox = new TextBox(player.lastName());
        panel.addComponent(lastNameBox);

        panel.addComponent(new Label("Height:"));
        TextBox heightBox = new TextBox(String.valueOf(player.height()));
        panel.addComponent(heightBox);

        panel.addComponent(new Label("Weight:"));
        TextBox weightBox = new TextBox(String.valueOf(player.weight()));
        panel.addComponent(weightBox);

        panel.addComponent(new Label("Jersey Number:"));
        TextBox jerseyBox = new TextBox(String.valueOf(player.jerseyNumber()));
        panel.addComponent(jerseyBox);

        panel.addComponent(new Label("Position:"));
        TextBox positionBox = new TextBox(player.position());
        panel.addComponent(positionBox);

        panel.addComponent(new Label("Team ID:"));
        TextBox teamIdBox = new TextBox(String.valueOf(player.teamId()));
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

            if (firstName.isBlank() || lastName.isBlank() || heightText.isBlank()
                    || weightText.isBlank() || jerseyText.isBlank()
                    || position.isBlank() || teamIdText.isBlank()) {
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

                service.updatePlayer(
                        player.id(),
                        firstName,
                        lastName,
                        height,
                        weight,
                        jersey,
                        position,
                        teamId
                );

                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Success",
                        "Player updated successfully!",
                        MessageDialogButton.OK
                );

                ui.closeWindow(this);

            } catch (NumberFormatException e) {
                MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Error",
                        "Height, Weight, Jersey number and Team ID must be numbers.",
                        MessageDialogButton.OK
                );
            }
        }));

        panel.addComponent(new Button("Back", () -> ui.closeWindow(this)));

        return panel;
    }
}