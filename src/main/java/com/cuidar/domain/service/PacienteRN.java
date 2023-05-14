package com.cuidar.domain.service;

import com.cuidar.domain.Exceptions.PacienteNotFoundException;
import com.cuidar.domain.model.exame.ExameED;
import com.cuidar.domain.repository.PacienteDB;
import com.cuidar.domain.model.paciente.PacienteED;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteRN {

    @Autowired
    private PacienteDB pacienteDB;

    public PacienteRN(PacienteDB pacienteDB) {
        this.pacienteDB = pacienteDB;
    }

    public Optional<PacienteED> obterPacientePorId(Long id) {
        return pacienteDB.findPacienteById(id);
    }

    public List<PacienteED> obterTodosPacientes() {
        return pacienteDB.findAll();
    }

    public List<PacienteED> obterTodosPacientesAtivos(Boolean isAtivo) {
        return pacienteDB.findAllPacientesByIsAtivo(isAtivo);
    }

    public List<PacienteED> obterTodosPacientesPorNome(String nome) {
        return pacienteDB.findAllPacientesByNomeContainingIgnoreCase(nome);
    }

    public List<PacienteED> obterTodosPacientesPorExame(String nomeExame) {
        return pacienteDB.findAllPacientesBynomeExameContainingIgnoreCase(nomeExame);

    }

    public List<PacienteED> obterTodosPacientesPorEndereco(String endRua) {
        return pacienteDB.findAllPacientesByEnderecoEndRuaContainingIgnoreCase(endRua);

    }

    public PacienteED salvarPaciente(PacienteED pacienteED) {
        List<ExameED> exames = pacienteED.getExames();
        pacienteED.setExames(null);

        PacienteED pacienteSalvo = pacienteDB.save(pacienteED);
        exames.forEach(exame -> exame.setPaciente(pacienteSalvo));
        pacienteSalvo.setExames(exames);

        pacienteDB.save(pacienteSalvo);
        return pacienteSalvo;
    }


    public PacienteED atualizarPaciente(PacienteED pacienteED) {
        return pacienteDB.save(pacienteED);
    }



    public void deletarLogicoPaciente(Long id){
        PacienteED pacienteParaDesativar = pacienteDB.findPacienteById(id).orElseThrow(() -> new PacienteNotFoundException("Paciente", id));
        pacienteParaDesativar.setIsAtivo(false);
        pacienteDB.save(pacienteParaDesativar);
    }

}
