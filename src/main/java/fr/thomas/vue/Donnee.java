package fr.thomas.vue;

import fr.thomas.modele.entity.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Donnee {

    @Getter
    public static Player playerStatic = null;

    @Getter
    @Setter
    public Player player;

}
