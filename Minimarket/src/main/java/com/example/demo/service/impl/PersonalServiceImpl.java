package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Persona;
import com.example.demo.entity.TipoPersona;
import com.example.demo.repository.PersonalRepository;
import com.example.demo.service.PersonalService;

@Service("personalService")
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    @Qualifier("personalRepository")
    private PersonalRepository personalRepository;

    @Override
    public void guardarPersonal(Persona persona) {
        personalRepository.save(persona);
    }

    @Override
    public List<Persona> listarPersonal() {
        return personalRepository.findByTipo(TipoPersona.PERSONAL);
    }

    @Override
    public Persona buscarPersonal(int id) {
        return personalRepository.findById(id).get();
    }

    @Override
    public void actualizarPersonal(Persona persona) {
        personalRepository.save(persona);
    }

    @Override
    public void eliminarPersonal(int id) {
        personalRepository.deleteById(id);
    }

}
