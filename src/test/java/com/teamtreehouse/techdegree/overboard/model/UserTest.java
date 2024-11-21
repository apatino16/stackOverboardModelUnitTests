package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

// Test Fixture for the User model
public class UserTest {
    // Initialized Users
    private User answerer;
    private User questioner;
    private User voter;

    private Question question;
    private Answer answer;


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        Board board = new Board("Taekwondo");
        questioner = board.createUser("Questioner");
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

    // Test that Authors are prevented from self-voting

    // Verify that users cannot upVote their own question
    @Test
    public void userUpVotesTheirOwnQuestion() throws Exception {

        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        questioner.upVote(question);
    }
    
    // Verify that user cannot downVote their own question
    @Test
    public void userDownVotesTheirOwnQuestion() throws Exception {

        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        questioner.downVote(question);
    }

    // Verify that user cannot upVote their own answer
    @Test
    public void userUpVotesTheirOwnAnswer() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        answerer.upVote(answer);

    }

    // Verify that user cannot downVote their own answer

    @Test
    public void userDownVotesTheirOwnAnswer() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        answerer.downVote(answer);

    }
}
