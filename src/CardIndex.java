import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardIndex {
    private List<IndividualEntity> individualEntities;
    private List<LegalEntity> legalEntities;

    public CardIndex() {
        individualEntities = new ArrayList<>();
        legalEntities = new ArrayList<>();
    }

    public void addIndividual(int x, int y) {
        individualEntities.add(new IndividualEntity(x, y));
    }

    public void addLegal(int x, int y) {
        legalEntities.add(new LegalEntity(x, y));
    }

    public List<IndividualEntity> getIndividualEntities() {
        return individualEntities;
    }

    public List<LegalEntity> getLegalEntities() {
        return legalEntities;
    }

    public int getTotalEntities() {
        return individualEntities.size() + legalEntities.size();
    }
}
