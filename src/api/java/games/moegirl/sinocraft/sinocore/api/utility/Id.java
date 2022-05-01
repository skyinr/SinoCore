package games.moegirl.sinocraft.sinocore.api.utility;

public class Id {

    private int id;

    public synchronized int nextId() {
        return id++;
    }
}
