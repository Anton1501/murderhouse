package room.view;

import java.util.Optional;
import item.Item;

public abstract class Human extends View {
    
    /**
     * Gibt eine Reaktion auf ein Item zurück.
     * 
     * @param heldItem Das Item.
     * @return Reaktion auf das Item.
     */
    public abstract String talk(Optional<Item> heldItem);
}
