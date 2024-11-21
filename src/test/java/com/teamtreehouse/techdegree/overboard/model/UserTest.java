package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Test Fixture for the User model
public class UserTest {
    private Board board;
    private User questioner;
    private User answerer;
    private User voter;
    private Question question;
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        board = new Board("Taekwondo");
        questioner = board.createUser("Questioner");
        answerer = board.createUser("Answerer");
        voter = board.createUser("Voter");

        // Arrange
        Question question = questioner.askQuestion("What is Taekwondo?");
        Answer answer = answerer.answerQuestion(question, "Taekwondo, 'the way of foot and fist,' is Korean martial art and Olympic sport that involves kicking, punching, and blocking techniques");

    }

    // Test for Reputation Changes

    // Validates that upvoting a question increases the questioner's reputation by 5 points
    @Test
    public void questionerReputationIncreasesBy5WhenQuestionIsUpVoted() {
        // Act
        voter.upVote(question);

        // Assert
        assertEquals(5, questioner.getReputation());

    }

    // Comfirms that upvoting an answer adds 10 points to the answerer's reputation
    @Test
    public void questionerReputationIncreasesBy10WhenAnswerIsUpVoted() {    
        // Act
        voter.upVote(answer);

        // Assert
        assertEquals(10, answerer.getReputation());

    }

    // Tests if accepting an answer adds 15 points to the answerer's reputation
    @Test
    public void acceptedAnswerGives15ReputationBoost() {
        // Act
        questioner.acceptAnswer(answer);

        // Assert
        asserEquals(15, answerer.getReputation());
    
    }


}
