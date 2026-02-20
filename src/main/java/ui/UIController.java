package ui;

import backend.services.StatsService;
import com.googlecode.lanterna.gui2.Window;
import ui.windows.AllPlayersWindow;
import ui.windows.PlayerStatsWindow;
import ui.windows.MainWindow;

/*
Handles navigation
 */
public class UIController {

    private final Gui gui;
    private final StatsService statsService;

    public UIController(Gui gui, StatsService statsService) {
        this.gui = gui;
        this.statsService = statsService;
    }

    public void showMainMenu() {
        gui.show(new MainWindow(this));
    }

    public void showAllPlayersPage() { gui.show(new AllPlayersWindow(this, statsService)); }

    public void showPlayerStatsPage() { gui.show(new PlayerStatsWindow(this, statsService)); }

    public void closeWindow(Window window) {
        window.close();
    }

    public void closeApp() {
        gui.close();
    }
}
