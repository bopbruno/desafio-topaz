package br.com.encurtadorurl.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "url",
        uniqueConstraints = @UniqueConstraint(name = "uk_url_encurtada", columnNames = "url_encurtada")
)
@Getter
@NoArgsConstructor
public class URL implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url_encurtada", nullable = false, updatable = false, length = 64)
    private String urlEncurtada;

    @Column(name = "url_original", nullable = false, length = 2048)
    private String urlOriginal;

    public URL(String urlEncurtada, String urlOriginal) {
    	this.urlEncurtada = urlEncurtada;
    	this.urlOriginal = urlOriginal;
    }

}
