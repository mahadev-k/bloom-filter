package com.dev.bloomfilter.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uniqueKey should be unique", columnNames = {"uniqueKey"})
        }
)
public class BloomState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Transient
    private Integer bucketLength;

    @Column(nullable = false)
    private Boolean buckets[];

    @Column
    private String uniqueKey;

    @Version
    private Integer version;

    @PostLoad
    private void init() {
        setBucketLength(this.buckets);
    }

    private void setBucketLength(Boolean buckets[]){
        if(Objects.isNull(buckets)){
            this.bucketLength = 0;
        }else{
            this.bucketLength = buckets.length;
        }
    }

}
