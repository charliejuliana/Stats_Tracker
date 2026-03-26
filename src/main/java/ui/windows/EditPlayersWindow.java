package ui.windows;

import com.googlecode.lanterna.gui2.*;
import ui.UIController;

import java.util.List;

public class EditPlayersWindow extends BasicWindow {

    private final UIController ui;

    public EditPlayersWindow(UIController ui, String title) {
        super(title);
        this.ui = ui;
        setHints(List.of(Window.Hint.CENTERED, Hint.EXPANDED, Hint.NO_POST_RENDERING));
        setComponent(build());
    }

    public EditPlayersWindow(UIController ui) {
        this(ui, "Edit Players");
    }

    private record MenuItem(String name, Runnable func) {
    }

    private Component build() {
        Panel panel = new Panel();
        panel.setLayoutManager(
                new LinearLayout(Direction.VERTICAL)
        );

        MenuItem[] menu = {
                new MenuItem("Add", ui::addPlayersPage),
                new MenuItem("Delete", ui::deletePlayersPage),
                new MenuItem("Update", ui::choosePlayerToUpdatePage),
        };

        for (MenuItem mi : menu) {
            panel.addComponent(new Button(mi.name, mi.func));
        }

        panel.addComponent(new Button("Back", () -> ui.closeWindow(this)));

        return panel;
    }

    private void NoOp() {
    }
}
