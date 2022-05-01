package com.example.converter;

import java.util.function.Function;

public class BaseConverter<Model, Entity> {

    private final Function<Model, Entity> fromModel;
    private final Function<Entity, Model> fromEntity;

    public BaseConverter(Function<Model, Entity> fromModel, Function<Entity, Model> fromEntity) {
        this.fromModel = fromModel;
        this.fromEntity = fromEntity;
    }

    public final Entity convertFromModel(final Model model) {
        return fromModel.apply(model);
    }

    public final Model convertFromEntity(final Entity entity) {
        return fromEntity.apply(entity);
    }

}
