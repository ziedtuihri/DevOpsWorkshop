package tn.esprit.tpfoyer.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    private Universite universite;

    @BeforeEach
    void setUp() {
        universite = new Universite();
        universite.setIdUniversite(1L);
        universite.setNomUniversite("University A");
        universite.setAdresse("123 University St");
    }

    @Test
    void testRetrieveAllUniversites() {
        List<Universite> universites = Arrays.asList(universite);
        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(universites, result);
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveUniversite() {
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1L);

        assertNotNull(result);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    void testAddUniversite() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertNotNull(result);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testModifyUniversite() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.modifyUniversite(universite);

        assertNotNull(result);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRemoveUniversite() {
        universiteService.removeUniversite(1L);

        verify(universiteRepository, times(1)).deleteById(1L);
    }

}