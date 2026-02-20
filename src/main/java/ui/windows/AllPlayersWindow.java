package ui.windows;

import backend.services.StatsService;
import com.googlecode.lanterna.gui2.*;
import models.Player;
import ui.UIController;

import java.util.List;

public class AllPlayersWindow extends BasicWindow {

    private final UIController ui;
    private final StatsService service;

    public AllPlayersWindow(UIController ui, StatsService service) {
        super("Who's Stats Do You Want To See?");
        this.ui = ui;
        this.service = service;
        setHints(List.of(Hint.CENTERED));
        setComponent(build());
    }

    private record MenuItem(String name, Runnable func) {
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
            alb.addItem(p.firstName() + " " + p.lastName(), ui::showPlayerStatsPage);
        }
        alb.addItem("Back", () -> ui.closeWindow(this));

        return panel;
    }
}
