package mage.controller;

import java.util.Optional;

import mage.entity.Mage;
import mage.repository.MageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


public class MageControllerTest {
    private MageRepository mageRepository;
    private MageController mageController;

    @BeforeEach
    public void initTestData() {
        mageRepository = mock(MageRepository.class);
        mageController = new MageController(mageRepository);
    }

    @Test
    public void find_MageExistsInCollection_MageToString() {
        Optional<Mage> optMage = Optional.of(new Mage("Mage1", 1));
        when(mageRepository.find("Mage1")).thenReturn(optMage);

        String feedback = mageController.find("Mage1");

        assertThat(feedback).isEqualTo(optMage.get().toString());
        verify(mageRepository, times(1)).find("Mage1");
    }

    @Test
    public void find_MageNotExistsInCollection_NotFound() {
        when(mageRepository.find("Mage1")).thenReturn(Optional.empty());

        String feedback = mageController.find("Mage1");

        assertThat(feedback).isEqualTo("not found");
        verify(mageRepository, times(1)).find("Mage1");
    }

    @Test
    public void delete_MageExistsInCollection_Done() {
        String feedback = mageController.delete("Mage1");

        assertThat(feedback).isEqualTo("done");
        verify(mageRepository, times(1)).delete("Mage1");
    }

    @Test
    public void delete_MageNotExistsInCollection_IllegalArgumentException() {
        doThrow(IllegalArgumentException.class)
                .when(mageRepository)
                .delete("Mage1");

        String feedback = mageController.delete("Mage1");

        assertThat(feedback).isEqualTo("not found");
        verify(mageRepository, times(1)).delete("Mage1");
    }

    @Test
    public void save_MageExistsInCollection_BadRequest() {
        Mage mage = new Mage("Mage1", 1);
        doThrow(IllegalArgumentException.class)
                .when(mageRepository)
                .save(mage);

        String feedback = mageController.save(mage.getName(), Integer.toString(mage.getLevel()));

        assertThat(feedback).isEqualTo("bad request");
        verify(mageRepository, times(1)).save(mage);
    }

    @Test
    public void save_MageNotExistsInCollection_Done() {
        Mage mage = new Mage("Mage1", 1);

        String feedback = mageController.save(mage.getName(), Integer.toString(mage.getLevel()));

        assertThat(feedback).isEqualTo("done");
        verify(mageRepository, times(1)).save(mage);
    }
}