import java.util.ArrayList;
import java.util.List;

public class Customer {

    private List<Cd> cdCollection;

    public Customer() {
        this.cdCollection = new ArrayList<>();
    }

    public boolean hasCd(Cd cd) {
        return cdCollection.contains(cd);
    }

    public void addToCollection(Cd cd) {
        cdCollection.add(cd);
    }
}
