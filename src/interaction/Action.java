package interaction;

import java.util.Optional;

import item.Item;

public abstract class Action {
    /**
     * Gibt die Eingabe vom Spieler zurück.
     * 
     * @return Die Eingabe vom Spieler.
     */
    public abstract String getQuery();

    /**
     * Gibt das vom Spieler gehaltene Item zurück.
     * 
     * @return Gehaltenes Item
     */
    public abstract Optional<Item> getHeldItem();
}
