package ui.windows;

import com.googlecode.lanterna.gui2.*;
import ui.UIController;

import java.util.List;

public class EditTeamsWindow extends BasicWindow {

    private final UIController ui;

    public EditTeamsWindow(UIController ui, String title) {
        super(title);
        this.ui = ui;
        setHints(List.of(Window.Hint.CENTERED, Hint.EXPANDED, Hint.NO_POST_RENDERING));
        setComponent(build());
    }

    public EditTeamsWindow(UIController ui) {
        this(ui, "Edit Teams");
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
