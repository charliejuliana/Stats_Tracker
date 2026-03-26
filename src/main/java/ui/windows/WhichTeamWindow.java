package ui.windows;

import backend.services.StatsService;
import com.googlecode.lanterna.gui2.*;
import models.Team;
import ui.UIController;

import java.util.List;

public class WhichTeamWindow extends BasicWindow {

    private final UIController ui;
    private final StatsService service;

    public WhichTeamWindow(UIController ui, StatsService service) {
        super("Which Team Do You Want to View?");
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

        List<Team> teams = service.getAllTeams();

        ActionListBox alb = new ActionListBox();
        panel.addComponent(alb);

        for (Team p : teams) {
            alb.addItem(p.name(), ui::showPlayerStatsPage);
        }

        alb.addItem("Back", () -> ui.closeWindow(this));

        return panel;
    }
}
