package logic.ai;

public class AIFactory {

    public AI create(int i) {
        AI ai = null;

        switch (i) {
            case 0 : ai = new NotVerySmartAI();
            break;
            case 1 : ai = new ABitSmarterAI();
            break;
            case 2 : ai = new QuiteSmartAI();
            break;
        }

        return ai;
    }
}
