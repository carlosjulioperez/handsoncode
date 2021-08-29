package ec.carper.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class Post {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(mappedBy = "post")
    private List<Topic> topics;

	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String text;
	
}
