package net.minecraftforge.gradle.userdev.util;

import org.gradle.api.attributes.Attribute;
import org.gradle.api.attributes.AttributeContainer;
import org.gradle.api.provider.Provider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Set;

public class MutableAttributeContainer implements AttributeContainer {
    private final HashMap<Attribute<?>, Object> values = new HashMap<>();

    public void attributes(AttributeContainer container) {
        Set<Attribute<?>> keys = container.keySet();
        for (Attribute<?> key : keys) {
            values.put(key, container.getAttribute(key));
        }
    }

    @Override
    public @Nonnull Set<Attribute<?>> keySet() {
        return values.keySet();
    }

    @Override
    public <T> @Nonnull AttributeContainer attribute(@Nonnull Attribute<T> key, @Nonnull T value) {
        values.put(key, value);
        return this;
    }

    @Override
    public <T> @Nonnull AttributeContainer attributeProvider(@Nonnull Attribute<T> key,
                                                             @Nonnull Provider<? extends T> provider) {
        values.put(key, provider.get());
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> @Nullable T getAttribute(@Nonnull Attribute<T> key) {
        Object value = values.get(key);
        if (value == null || !key.getType().isInstance(value)) {
            return null;
        }
        return (T) value;
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean contains(@Nonnull Attribute<?> key) {
        return values.containsKey(key);
    }

    @Override
    public @Nonnull AttributeContainer getAttributes() {
        return this;
    }
}
