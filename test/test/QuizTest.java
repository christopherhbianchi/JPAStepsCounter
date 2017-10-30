package test;

import static org.junit.Assert.*;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Question;
import entities.Quiz;


public class QuizTest {
	EntityManagerFactory emf;
	EntityManager em;
	
	@Before
	public void setUp() throws Exception { //the EMFactory and EM
		emf = Persistence.createEntityManagerFactory("JPAQuiz"); //Refers to name in Persistence file
		em = emf.createEntityManager();
		
	}
	
	@After
	public void tearDown() throws Exception{ //close our resources
		em.close();
		emf.close();
	}
	
	@Test
	public void testQuiz() {
		Quiz quiz = em.find(Quiz.class, 10); //grab a quiz obj from the DB
		assertEquals("States", quiz.getName()); //the name should match whats in quotes
	}
	
	@Test //see if we can get a set of questions
	public void testRetrieveSetOfQuestions() {
		Quiz quiz = em.find(Quiz.class, 10);
		Set<Question> questionSet = quiz.getQuestionSet();
		assertTrue(questionSet.size() > 0);
	}

}
