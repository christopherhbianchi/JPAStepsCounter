package entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String questionText;
	
	//we use Sets to prevent duplicate objects
	//so whenever we make a change to the Question object, its answers get deleted too
	@OneToMany(mappedBy="question", cascade= {CascadeType.PERSIST, CascadeType.REMOVE}) 
	@JsonManagedReference
	private Set<Answer> answerSet;
	
	@ManyToOne
	@JsonIgnore //don't want our question to have a quiz object in the DB?
	@JoinColumn(name="quiz_id")
	private Quiz quiz;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public Set<Answer> getAnswerSet() {
		return answerSet;
	}
	public void setAnswerSet(Set<Answer> answerSet) {
		this.answerSet = answerSet;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

}
