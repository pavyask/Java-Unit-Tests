package mage.controller;

import lombok.*;
import mage.entity.Mage;
import mage.repository.MageRepository;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MageController {
    private MageRepository repository;

    public String find(String name) {
        Optional<Mage> optMage = repository.find(name);
        if (optMage.isPresent()) {
            return optMage.get().toString();
        }
        return "not found";
    }

    public String delete(String name) {
        try {
            repository.delete(name);
            return "done";
        } catch (IllegalArgumentException ex) {
            return "not found";
        }
    }

    public String save(String name, String level) {
        try {
            repository.save(new Mage(name, Integer.parseInt(level)));
            return "done";
        } catch (IllegalArgumentException ex) {
            return "bad request";
        }
    }
}