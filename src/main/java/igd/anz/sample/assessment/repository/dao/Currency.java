package igd.anz.sample.assessment.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "currency")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date", insertable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "update_date", insertable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date updateDate;
}
