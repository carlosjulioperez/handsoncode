package ec.carper.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class Topic{
	@Id
	@GeneratedValue
	private long id;
	    
	@Column(nullable=false)
	private String name;
    
	@ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
