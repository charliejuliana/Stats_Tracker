package ui.windows;

import backend.services.StatsService;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import models.PlayerStats;
import ui.UIController;

import java.util.List;

public class PlayerStatsWindow extends BasicWindow {

    private final UIController ui;
    private final StatsService service;

    public PlayerStatsWindow(UIController ui, StatsService service) {
        super("Total Stats");
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

        List<PlayerStats> playerStats = service.getAllPlayerStats();
        int points = 0;
        int assists = 0;
        int rebounds = 0;
        int steals = 0;
        int blocks = 0;


        TextBox tb = new TextBox(new TerminalSize(30, 5));
        panel.addComponent(tb);

        for (PlayerStats pS : playerStats) {
            points += pS.points();
            assists += pS.assists();
            rebounds += pS.rebounds();
            steals += pS.steals();
            blocks += pS.blocks();
        }
        tb.setText("Points: " + points + "\nAssists: " + assists + "\nRebounds: " + rebounds + "\nSteals: " + steals + "\nBlocks: " + blocks);
        ActionListBox alb = new ActionListBox();
        panel.addComponent(alb);

        alb.addItem("Back", () -> ui.closeWindow(this));

        return panel;
    }
}