package fr.thomas.controller;

import fr.thomas.controller.listeners.KeyListener;
import fr.thomas.controller.managers.GameManager;
import fr.thomas.controller.managers.MenusManager;
import fr.thomas.controller.managers.OptionsManager;
import fr.thomas.controller.tasks.EnemyTask;
import fr.thomas.controller.tasks.GameTask;
import fr.thomas.controller.tasks.VisitedTasks;
import fr.thomas.modele.entity.Player;
import fr.thomas.modele.game.Game;
import fr.thomas.modele.game.GameState;
import fr.thomas.modele.map.Localizable;
import fr.thomas.modele.map.entity.Visited;
import fr.thomas.modele.map.save.SaveUtils;
import fr.thomas.modele.map.save.Saves;
import fr.thomas.utils.Utils;
import fr.thomas.exceptions.MovementException;
import fr.thomas.vue.VueElement;
import fr.thomas.vue.VuePlayer;
import fr.thomas.vue.entity.VueVisited;
import fr.thomas.vue.menus.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

@Getter
@Setter
public class Controller implements Initializable {

    private java.util.Map<String, VueElement> vueElements = new HashMap<>();

    private VueMenuGame vueMenuGame;

    private VueMenuMain vueMenuMain;

    private VueMenuOptions vueMenuOptions;

    private VueMenuEndGame vueMenuEndGame;

    private VueMenuPause vueMenuPause;

    private VueMenuHistory vueMenuHistory;

    private Game game;

    private OptionsManager optionsManager;

    private MenusManager menusManager;

    private GameManager gameManager;

    private GameState gameState;

    @FXML
    private Pane gameScreen;

    @FXML
    private Button play;

    @FXML
    private Button saveGame;

    @FXML
    private Button cancelMovement;

    @FXML
    private Button returnMenu;

    @FXML
    private Button options;

    @FXML
    private Button gameNext;

    @FXML
    private Button continueGame;

    @FXML
    private Button history;

    @FXML
    private Button menu;

    @FXML
    private Button saveEndGame;

    @FXML
    private Text powerKey;

    @FXML
    private ProgressBar powerBar;

    @FXML
    private TextArea textChat;

    @FXML
    private Text textInfo;

    @FXML
    private Text moveValue;

    @FXML
    private Text moveKey;

    @FXML
    private Text endInfos;

    @FXML
    private TextField topBind;

    @FXML
    private TextField bottomBind;

    @FXML
    private TextField rightBind;

    @FXML
    private TextField leftBind;

    @FXML
    private Text topKey;

    @FXML
    private Text bottomKey;

    @FXML
    private Text rightKey;

    @FXML
    private Text leftKey;

    @FXML
    private ListView<Game> historyList;

    @FXML
    private CheckBox autoSave;

    @FXML
    private Button resetReview;

    @FXML
    private Button animateReview;

    private GameTask task;

    @FXML
    private Slider animateSpeedReview;

    @FXML
    private ImageView background;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Task
        task = null;

        // History
        refreshHistory();

        // Managers
        optionsManager = new OptionsManager(this);
        optionsManager.load();
        gameManager = new GameManager(this);
        menusManager = new MenusManager(this);

        // Vues
        initVues();

        // Listeners
        gameScreen.setFocusTraversable(true);
        gameScreen.setOnKeyPressed(new KeyListener(this));

        // Default State
        menusManager.setGameState(GameState.MENU);

        // Chat
        textChat.setFocusTraversable(true);

        // Continue
        continueGame.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.PLAY);
        });

        menu.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.MENU);
        });

        // Jouer
        play.setOnMouseClicked(event -> {
            // NEW GAME
            gameManager.clearLastGameMap();
            gameManager.createGame();

            gameManager.generateMap();
            menusManager.setGameState(GameState.PLAY);

        });

        // Slider
        animateSpeedReview.valueProperty().addListener((obs, oldval, newVal) ->
                animateSpeedReview.setValue(newVal.intValue()));

        // Cancel Movement
        cancelMovement.setOnMouseClicked(event -> {
            if (game.isEnd()) {
                addChatLine("Impossible, partie terminée !");
                return;
            }
            try {
                game.getPlayer().cancelMovement(1);
                addChatLine("Retour en arrière !");
            } catch (MovementException e) {
                addChatLine("Impossible de retourner en arrière !");
            }
        });

        // Options
        options.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.OPTIONS);
        });

        // Historique
        history.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.HISTORY);
        });

        historyList.setOnMouseClicked(event -> {
            if (historyList.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            game = SaveUtils.loadSave(historyList.getSelectionModel().getSelectedItem().getName());

            if (game == null) {
                return;
            }

            if (event.isControlDown()) {
                SaveUtils.delete(game.getName());
                refreshHistory();
                return;
            }

            gameManager.clearLastGameMap();
            gameManager.createGameFromSave(game);
            gameManager.placeElements();
            if (game.isEnd()) {
                new VisitedTasks(this, false).start();
            }
            menusManager.setGameState(GameState.PLAY);
        });


        gameNext.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.MENU);
        });

        resetReview.setOnMouseClicked(event -> {
            if (task != null) {
                task.stop();
            }
            removeVisiteds();
            addChatLine("Animation réinitialisé !");
        });

        animateReview.setOnMouseClicked(event -> {
            if (task != null) {
                addChatLine("Impossible, une animation est en cours !");
                return;
            }
            removeVisiteds();
            new VisitedTasks(this, false).start();
        });

        // Return
        returnMenu.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.MENU);
            optionsManager.save();
        });

        saveGame.setOnMouseClicked(event -> {
            gameManager.save(game);
        });
        saveEndGame.setOnMouseClicked(event -> {
            gameManager.save(game);
        });
    }

    /**
     * Add a formated message in the chat text area
     */
    public void addChatLine(String message) {
        textChat.appendText(Utils.getTime() + ": " + message + "\n");
    }


    /**
     * Create view and add Node to each view
     */
    private void initVues() {
        vueMenuGame = new VueMenuGame(gameScreen);
        vueMenuGame.addNode(textChat, powerKey, powerBar, cancelMovement, moveKey, moveValue, resetReview, animateReview, animateSpeedReview);
        vueMenuGame.add();

        vueMenuMain = new VueMenuMain(gameScreen);
        vueMenuMain.addNode(play, options, history, background);
        vueMenuMain.add();

        vueMenuOptions = new VueMenuOptions(gameScreen);
        vueMenuOptions.addNode(returnMenu, topBind, bottomBind, leftBind, rightBind, topKey, bottomKey, rightKey, leftKey, autoSave);
        vueMenuOptions.add();

        vueMenuEndGame = new VueMenuEndGame(gameScreen);
        vueMenuEndGame.addNode(gameNext, endInfos, saveEndGame);
        vueMenuEndGame.add();

        vueMenuPause = new VueMenuPause(gameScreen);
        vueMenuPause.addNode(continueGame, saveGame, menu, background);
        vueMenuPause.add();

        vueMenuHistory = new VueMenuHistory(gameScreen);
        vueMenuHistory.addNode(returnMenu, historyList, background);
        vueMenuHistory.add();
    }

    /**
     * Refresh the history list from the .txt files il the data/games folder
     */
    public void refreshHistory() {
        historyList.getItems().clear();
        SaveUtils.getSavedGames().forEach(s -> {
            Game g = SaveUtils.loadSave(s);
            historyList.getItems().add(g);
        });
        historyList.refresh();
    }

    /**
     * Place ImageViews at visiteds locations in the map
     */
    public VueVisited placeVisiteds(int needed, double brightness) {

        for (int i = 0; i < game.getPlayer().getVisited().size(); i++) {
            Localizable localizable = game.getPlayer().getVisited().get(i);

            if (i == needed) {
                Visited empty = new Visited(localizable.getX(), localizable.getY());

                ColorAdjust blackout = new ColorAdjust();
                blackout.setBrightness(brightness);


                VueVisited vueVisited = new VueVisited(empty, gameScreen);
                vueVisited.add();

                vueVisited.getImageView().setEffect(blackout);
                vueElements.put(vueVisited.getId(), vueVisited);

                return vueVisited;
            }
        }
        return null;
    }

    public void removeVisiteds() {
        List<VueElement> toRemove = new ArrayList<>();
        vueElements.forEach((s, vueElement) -> {
            if (vueElement instanceof VueVisited) {
                vueElement.hide();
                toRemove.add(vueElement);
            }
        });

        for (VueElement vueElement : toRemove) {
            vueElements.remove(vueElement.getId());
        }
    }
}
