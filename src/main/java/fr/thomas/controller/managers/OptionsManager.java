package fr.thomas.controller.managers;

import fr.thomas.controller.Controller;
import fr.thomas.utils.options.Options;
import fr.thomas.utils.options.OptionsUtils;
import javafx.scene.control.TextField;
import lombok.Getter;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Getter
public class OptionsManager {

    private Controller controller;

    public OptionsManager(Controller controller) {
        this.controller = controller;

        List<TextField> fields = Arrays.asList(controller.getTopBind(), controller.getBottomBind(), controller.getRightBind(), controller.getLeftBind());

        fields.forEach(field -> {
            field.setOnKeyReleased(event -> {
                String current = field.getText();
                String entryKey = event.getText().toUpperCase();

                // Options is null
                Options options = Options.getFromId(field.getId());
                if (options == null) {
                    return;
                }

                // Already Bind
                if (OptionsUtils.isBind(entryKey)) {
                    field.clear();
                    field.setText(current);
                    return;
                }

                field.clear();
                field.setText(event.getText().toUpperCase());

                OptionsUtils.optionValues.put(options, entryKey);
            });
        });
    }

    public void load() {
        OptionsUtils.createDefaultIfNotExist();
        OptionsUtils.loadOptions();

        controller.getTopBind().setText(OptionsUtils.optionValues.get(Options.MOVE_TOP));
        controller.getBottomBind().setText(OptionsUtils.optionValues.get(Options.MOVE_BOTTOM));
        controller.getRightBind().setText(OptionsUtils.optionValues.get(Options.MOVE_RIGHT));
        controller.getLeftBind().setText(OptionsUtils.optionValues.get(Options.MOVE_LEFT));
    }

    public void save() {
        OptionsUtils.saveOptions();
    }

}
