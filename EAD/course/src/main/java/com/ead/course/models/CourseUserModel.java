package com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_CROUSES_USERS")
public class CourseUserModel {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;
    //@JoinColum(name= "course_id") caso queira renomear a coluna no banco
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CourseModel course;
    @Column(nullable = false)
    private UUID userId;
}
