package com.arjun.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "testing")
@GenericGenerator(name = "testing_seq" , strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
					parameters = {
							@Parameter(name="sequence_name",value = "testing_seq"),
							@Parameter(name="increment_size",value = "1")})
public class DemoEntity {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO,generator = "testing_seq")
	private Long id;
	
//	@Basic(fetch = FetchType.LAZY)
	@Column(name = "name")
	private String name;
	

}
