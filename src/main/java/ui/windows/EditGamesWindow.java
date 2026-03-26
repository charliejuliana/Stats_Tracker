package ui.windows;

import com.googlecode.lanterna.gui2.*;
import ui.UIController;

import java.util.List;

public class EditGamesWindow extends BasicWindow {

    private final UIController ui;

    public EditGamesWindow(UIController ui, String title) {
        super(title);
        this.ui = ui;
        setHints(List.of(Window.Hint.CENTERED, Hint.EXPANDED, Hint.NO_POST_RENDERING));
        setComponent(build());
    }

    public EditGamesWindow(UIController ui) {
        this(ui, "Edit Games");
    }

    private record MenuItem(String name, Runnable func) {
    }

    private Component build() {
        Panel panel = new Panel();
        panel.setLayoutManager(
                new LinearLayout(Direction.VERTICAL)
        );

        MenuItem[] menu = {
                new MenuItem("Add", this::NoOp),
                new MenuItem("Delete", this::NoOp),
                new MenuItem("Update", this::NoOp),
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
