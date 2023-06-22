package com.cuidar.domain.model.exame;

import com.cuidar.domain.model.atendente.AtendenteED;
import com.cuidar.domain.model.local.LocalED;
import com.cuidar.domain.model.medico.MedicoED;
import com.cuidar.domain.model.paciente.PacienteED;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_EXAME")
public class ExameED {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "ID_EXAME")
    private Long id;

    @NotBlank(message = " nomeExame {campo.texto.notBlank.obrigatorio}")
    @Column(name = "NOME_EXAME", nullable = false, length = 100)
    private String nomeExame;

//    @NotBlank(message = "medico {campo.texto.notBlank.obrigatorio}")
    @Column(name = "MEDICO", nullable = true)
    private MedicoED medico;

//    @NotBlank(message = "local {campo.texto.notBlank.obrigatorio}")
    @Column(name = "LOCAL", nullable = true)
    private LocalED local;

//    @JsonIgnoreProperties(value = {"nome", "dataNasc", "observacao", "valor", "dataExame","rg", "dataCadastro","isAtivo","indicacao", "local","medicoAtendente",
//            "atendente","aceite","observacao","convenio", "deficienciaFamilia", "deficiencia", "deficiente", "endereco","profissao", "contato","nomeResponsavelPaciente",
//            "filhos", "estadoCivil", "idade", "exames"})
////    @NotBlank(message = "paciente {campo.texto.notBlank.obrigatorio}")
////    @Column(name = "ID_PACIENTE", nullable = false)
//    @ManyToOne
//    @JoinColumn(name = "ID_PACIENTE")
//    private PacienteED paciente;

    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_EXAME", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataExame;

    @NotNull(message = "valor é obrigatório")
    @PositiveOrZero(message = "valor não pode ser negativo")
    @DecimalMin("0.00")
    @Digits(integer=10, fraction=2)
    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

//    @NotBlank(message = "atendente {campo.texto.notBlank.obrigatorio}")
    @Column(name = "ATENDENTE", nullable = true)
    private AtendenteED atendente;

    //    @ElementCollection
//    @CollectionTable(name = "TB_ATENDENTE", joinColumns = @JoinColumn(name = "ID_PACIENTE"))
//    @Column(name = "ID_ATENDENTE")
//    private List<String> atendente;
//
//    @ElementCollection
//    @CollectionTable(name = "TB_MEDICO_ATENDENTE", joinColumns = @JoinColumn(name = "ID_PACIENTE"))
//    @Column(name = "ID_MEDICO_ATENDENTE")
//    private List<String> medicoAtendente;

    @Column(name = "OBSERVACAO", nullable = true, length = 500)
    private String observacao;

}
