package fr.thomas.utils.options;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Options {

    MOVE_TOP("move_top", "topBind"),
    MOVE_BOTTOM("move_bottom", "bottomBind"),
    MOVE_RIGHT("move_right", "rightBind"),
    MOVE_LEFT("move_left", "leftBind");

    private final String key;
    private final String id;

    public static Options getFromId(String id) {
        for (Options options : values()) {
            if (options.getId().equalsIgnoreCase(id)) {
                return options;
            }
        }
        return null;
    }

    public static Options get(String value) {
        for (Options options : values()) {
            if (options.getKey().equalsIgnoreCase(value)) {
                return options;
            }
        }
        return null;
    }

    public static Options getFromLine(String line) {
        String value = line.split("=")[0];
        return get(value);
    }
}
