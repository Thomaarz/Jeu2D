package fr.thomas.modele.map;

import fr.thomas.modele.map.entity.Bloc;
import fr.thomas.modele.map.entity.House;
import fr.thomas.modele.map.entity.MapEntity;
import fr.thomas.modele.map.entity.Void;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class MapSolver {

    private final Map map;

    private List<Localizable> verified = new ArrayList<>();

    public boolean verifyNext(Localizable current) {
        List<Localizable> nexts = getNextCases(current);
        boolean houseNext = false;

        for (Localizable next : nexts) {
            if (map.getElement(next.getX(), next.getY()) instanceof House) {
                return true;
            }
        }

        for (Localizable next : nexts) {
            if (!houseNext) {
                houseNext = verifyNext(next);
            }
        }

        return houseNext;
    }

    public List<Localizable> getNextCases(Localizable current) {
        Localizable left = new Localizable(current.getX() - 1, current.getY());
        Localizable right = new Localizable(current.getX() + 1, current.getY());
        Localizable top = new Localizable(current.getX(), current.getY() - 1);
        Localizable bottom = new Localizable(current.getX(), current.getY() + 1);

        List<Localizable> nexts = Arrays.asList(left, right, top, bottom);
        List<Localizable> f = new ArrayList<>();

        nexts.forEach(n -> {
            if (!verified.contains(n) && !(map.getElement(n.getX(), n.getY()) instanceof Bloc) && !(map.getElement(n.getX(), n.getY()) instanceof Void)) {
                f.add(n);
            }
        });

        verified.addAll(f);
        return f;
    }
}
