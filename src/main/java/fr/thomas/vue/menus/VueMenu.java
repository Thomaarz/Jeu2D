package fr.thomas.vue.menus;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class VueMenu {

    private Pane gameScreen;

    private List<Node> nodes;

    public VueMenu(Pane gameScreen) {
        this.nodes = new ArrayList<>();
        this.gameScreen = gameScreen;
    }

    public void addNode(Node... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
    }

    public void removeNode(Node... nodes) {
        for (Node node : nodes) {
            gameScreen.getChildren().remove(node);
        }
        this.nodes.removeAll(Arrays.asList(nodes));
    }

    public void add() {
        nodes.forEach(node -> {
            if (!gameScreen.getChildren().contains(node)) {
                gameScreen.getChildren().add(node);
            }
        });
    }

    public void remove() {
        nodes.forEach(gameScreen.getChildren()::remove);
    }

    public void show() {
        nodes.forEach(node -> node.setVisible(true));
    }

    public void hide() {
        nodes.forEach(node -> node.setVisible(false));
    }

}
