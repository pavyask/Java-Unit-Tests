package mage.repository;

import lombok.*;
import mage.entity.Mage;

import java.util.Collection;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MageRepository {
    private Collection<Mage> collection;

    public Optional<Mage> find(String name) {
        for (Mage mage : collection) {
            if (mage.getName().equals(name)) {
                return Optional.of(mage);
            }
        }
        return Optional.empty();
    }

    public void delete(String name) {
        Optional<Mage> optMage = find(name);
        if (optMage.isPresent()) {
            collection.remove(optMage.get());
        } else {
            throw new IllegalArgumentException("Such object does not exist!");
        }
    }

    public void save(Mage mage) {
        if (find(mage.getName()).isEmpty()) {
            collection.add(mage);
        } else {
            throw new IllegalArgumentException("Such object already exists!");
        }
    }
}