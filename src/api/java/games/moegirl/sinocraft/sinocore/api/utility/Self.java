package games.moegirl.sinocraft.sinocore.api.utility;

public interface Self<T> {

    default T self() {
        return (T) this;
    }
}
