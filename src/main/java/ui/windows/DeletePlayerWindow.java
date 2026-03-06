package ui.windows;

import backend.services.StatsService;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import models.Player;
import ui.UIController;

import java.util.List;

public class DeletePlayerWindow extends BasicWindow {

    private final UIController ui;
    private final StatsService service;

    public DeletePlayerWindow(UIController ui, StatsService service) {
        super("Delete a Player");
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

        List<Player> players = service.getAllPlayers();

        ActionListBox alb = new ActionListBox();
        panel.addComponent(alb);

        for (Player p : players) {

            alb.addItem(p.firstName() + " " + p.lastName(), () -> {

                MessageDialogButton result = MessageDialog.showMessageDialog(
                        getTextGUI(),
                        "Confirm Delete",
                        "Are you sure you want to delete "
                                + p.firstName() + " " + p.lastName() + "?",
                        MessageDialogButton.Yes,
                        MessageDialogButton.No
                );

                if (result == MessageDialogButton.Yes) {

                    service.deletePlayer(p.id());

                    MessageDialog.showMessageDialog(
                            getTextGUI(),
                            "Success",
                            "Player deleted successfully.",
                            MessageDialogButton.OK
                    );

                    ui.closeWindow(this);
                    ui.deletePlayerPage();
                }
            });
        }

        alb.addItem("Back", () -> ui.closeWindow(this));

        return panel;
    }
}