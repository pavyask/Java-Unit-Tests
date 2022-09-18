package mage.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mage.entity.Mage;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MageRepositoryTest {
    private MageRepository mageRepository;

    @BeforeEach
    public void initTestData() {
        List<Mage> mages = List.of(new Mage("Mage1", 1));
        mageRepository = new MageRepository(mages);
    }

    @Test
    public void find_MageExistsInCollection_OptionalMage() {
        Mage mage = mageRepository.find("Mage1").get();

        assertThat(mage.getName()).isEqualTo("Mage1");
    }

    @Test
    public void find_MageNotExistsInCollection_OptionalEmpty() {
        Optional<Mage> emptyOpt = mageRepository.find("Mage2");

        assertThat(emptyOpt).isEqualTo(Optional.empty());
    }

    @Test
    public void delete_MageNotExistsInCollection_IllegalArgumentException() {
        assertThatThrownBy(() -> mageRepository.delete("Mage2")).hasMessage("Such object does not exist!");
    }

    @Test
    public void save_MageAlreadyExistsInCollection_IllegalArgumentException() {
        assertThatThrownBy(() -> mageRepository.save(new Mage("Mage1", 1))).hasMessage("Such object already exists!");
    }
}