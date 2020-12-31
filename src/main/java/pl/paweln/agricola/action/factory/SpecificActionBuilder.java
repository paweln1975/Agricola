package pl.paweln.agricola.action.factory;

import pl.paweln.agricola.action.SpecificAction;

public class SpecificActionBuilder<T extends SpecificAction> extends ActionBuilder<SpecificActionBuilder<T>> {
    public SpecificActionBuilder(Class<T> tClass) {
        try {
            super.action = tClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public SpecificActionBuilder self() {
        return super.self();
    }
}
