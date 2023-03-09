package igd.anz.assessment.repository.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "currency")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "create_date", insertable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date createDate;

    @Column(name = "update_date", insertable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date updateDate;
}
