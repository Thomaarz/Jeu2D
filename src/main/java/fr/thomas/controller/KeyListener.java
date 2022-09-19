package fr.thomas.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyListener implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent event) {
        System.out.println(event.getCharacter());
    }
}
