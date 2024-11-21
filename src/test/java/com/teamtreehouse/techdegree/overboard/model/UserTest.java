package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Test Fixture for the User model
public class UserTest {
    // Initialized Users
    private User answerer;
    private User questioner;
    private User voter;

    private Question question;
    private Answer answer;


    @Before
    public void setUp() throws Exception {
        Board board = new Board("Taekwondo");
        questioner = board.createUser( "Questioner");
        answerer = board.createUser("Answerer");
        voter = board.createUser("Voter");

        // Arrange
        question = questioner.askQuestion("What is Taekwondo?");
        answer = answerer.answerQuestion(question, "Taekwondo, 'the way of foot and fist,' is Korean martial art and Olympic sport that involves kicking, punching, and blocking techniques");

    }

    // Test for Reputation Changes

    // Validates Questioner's reputation increases by 5 points if  their question is up-voted
    @Test
    public void questionerReputationIncreasesBy5WhenQuestionIsUpVoted() throws Exception {
        // Act
        voter.upVote(question);

        // Assert
        assertEquals(5, questioner.getReputation());
    }

    // Validate Answerer's reputation increases by 10 points if their answer is up-voted
    @Test
    public void answererReputationIncreasesBy10WhenAnswerIsUpVoted() throws Exception {
        // Act
        voter.upVote(answer);

        // Assert
        assertEquals(10, answerer.getReputation());
    }

    // Validates Answerer's reputation increases by 15 points if their answer is accepted
    @Test
    public void answererReputationIncreasesBy15IfAnswerIsAccepted() throws Exception {
        // Act
        questioner.acceptAnswer(answer);

        // Assert
        assertEquals(15, answerer.getReputation());
    
    }


}
