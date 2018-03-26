package DTO;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AnnouncementDTO {
	@JsonInclude(Include.NON_NULL)
	private int announcementId;
	@JsonInclude (Include.NON_NULL)
	private String title;
	@JsonInclude (Include.NON_NULL)
	private String description;
	@JsonInclude (Include.NON_NULL) 
	private BigDecimal price;
	@JsonInclude (Include.NON_NULL)
	private int userId;
	@JsonInclude (Include.NON_NULL)
	private int statusId;
	@JsonInclude (Include.NON_NULL)
	private Date startDate;
	@JsonInclude (Include.NON_NULL)
	private Date endDate;
}
