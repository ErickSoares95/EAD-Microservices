package com.ead.course.models;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_COURSES")

public class CourseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseId;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 250)
    private String description;
    @Column
    private String imageUrl;
    @Column(nullable = false)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime creationDate;
    @Column(nullable = false)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime lastUpdateDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;
    @Column(nullable = false)
    private UUID userInstructor;
    /*
     * Set foi utilizado porque o hibernate trabalha melhor com ele, podendo assim trazer varias entidades relacionadas a
     * curso e também evita problemas de multiplas querys.
     * fetch type é como os dados vão ser carregados dos bancos de dados(EAGER and LAZY)
     * FetchMode: Select: um busca para o curso e uma busca para cada module do curso. Sempre lazy
     * Join: na mesa consulta curso e modulos. Sempre EAGER
     * SUBSLECT: Uma consulta para trazer o curso e outra para todos os mudulos. Sempre lazy
     * Modos de deletar. JPA 1 SELECT para cada deleção, uma query para o curso 1 para todos os modulos
     * @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) JPA
     * @OnDelete(action = OnDeleteAction.CASCADE) Banco de dados
     * Ou utilizado metodos customizados pecorrendo os repositories e deletando
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Campo não mostrado nos gets, apenas escrita
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<ModuleModel> modules;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<CourseUserModel> coursesUsers;

    public  CourseUserModel convertToCourseUserModel(UUID userId){
        return new CourseUserModel(null, userId, this);
    }

}
