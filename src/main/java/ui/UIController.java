package ui;

import backend.services.StatsService;
import com.googlecode.lanterna.gui2.Window;
import ui.windows.*;
import models.Player;

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

    public void editTeamsPage() { gui.show(new EditTeamsWindow(this)); }

    public void editPlayersPage() { gui.show(new EditPlayersWindow(this)); }

    public void editGamesPage() { gui.show(new EditGamesWindow(this)); }

    public void showAllPlayersPage() { gui.show(new AllPlayersWindow(this, statsService)); }

    public void whichTeamPage() { gui.show(new WhichTeamWindow(this, statsService)); }

    public void showPlayerStatsPage() { gui.show(new PlayerStatsWindow(this, statsService)); }

    public void addPlayersPage() { gui.show(new AddPlayerWindow(this, statsService)); }

    public void deletePlayersPage() { gui.show(new DeletePlayersWindow(this, statsService)); }

    public void choosePlayerToUpdatePage() { gui.show(new ChoosePlayerToUpdateWindow(this, statsService)); }

    public void updatePlayerPage(Player player) { gui.show(new ChoosePlayerToUpdateWindow(this, statsService)); }

    public void closeWindow(Window window) {
        window.close();
    }

    public void closeApp() {
        gui.close();
    }
}
