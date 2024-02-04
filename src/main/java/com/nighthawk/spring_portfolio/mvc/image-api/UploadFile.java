package com.nighthawk.spring_portfolio.mvc.profile;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String username;
	@Column
	private String ImageEncoder;
	public UploadFile(String username, String ImageEncoder) {
        this.username = username;
        this.ImageEncoder = ImageEncoder;
    }
	
	
}