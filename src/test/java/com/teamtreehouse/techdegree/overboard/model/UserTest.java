package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// Test Fixture for the User model
public class UserTest {
    // Shared test objects
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
        // Act: Voter upVotes the question
        voter.upVote(question);

        // Assert: Questioner's reputation increases by 5
        assertEquals(5, questioner.getReputation());
    }

    // Validate Answerer's reputation increases by 10 points if their answer is up-voted
    @Test
    public void answererReputationIncreasesBy10WhenAnswerIsUpVoted() throws Exception {
        // Act: Voter upVotes the answer
        voter.upVote(answer);

        // Assert: Answerer's reputation increases by 10
        assertEquals(10, answerer.getReputation());
    }

    // Validates Answerer's reputation increases by 15 points if their answer is accepted
    @Test
    public void answererReputationIncreasesBy15IfAnswerIsAccepted() throws Exception {
        // Act: Questioner accepts the answer
        questioner.acceptAnswer(answer);

        // Assert: Answerer's reputation increases by 15
        assertEquals(15, answerer.getReputation());
    }

    // ---------- Extra Credit Starts-------- //

    // currently down-voting of questions affects nothing.
    @Test
    public void questionerReputationRemainTheSameWhenQuestionIsDownVoted() throws Exception {
        // Act: Voter upVotes the question
        voter.downVote(question);

        // Assert: Questioner's reputation increases by 5
        assertEquals(0, questioner.getReputation());
    }

    // Reputation deduction for downVoted answers costs 1 point
    @Test
    public void answererReputationDecreasesBy1WhenAnswerIsDownVoted() throws Exception {
        // Act: Voter upVotes the answer
        voter.downVote(answer);

        // Assert: Answerer's reputation increases by 10
        assertEquals(-1, answerer.getReputation());
    }
    
    // ---------- Extra Credit Ends ------- //


    // Test for Self-voting Restrictions

    // Verify that users cannot upVote their own question
    @Test
    public void userUpVotesTheirOwnQuestion() throws Exception {
        // Act and Assert: Expect a VotingException with appropriate message
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        questioner.upVote(question);
    }

    // Verify that user cannot downVote their own question
    @Test
    public void userDownVotesTheirOwnQuestion() throws Exception {
        // Act and Assert: Expect a VotingException with appropriate message
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        questioner.downVote(question);
    }

    // Verify that user cannot upVote their own answer
    @Test
    public void userUpVotesTheirOwnAnswer() throws Exception {
        // Act and Assert: Expect a VotingException with appropriate message
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        answerer.upVote(answer);

    }

    // Verify that user cannot downVote their own answer
    @Test
    public void userDownVotesTheirOwnAnswer() throws Exception {
        // Act and Assert: Expect a VotingException with appropriate message
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        answerer.downVote(answer);

    }

    // Test for Answer Acceptance

    // Non-author user cannot accept an answer to a question
    @Test
    public void nonAuthorAcceptsAnswerToQuestion() throws Exception {
        // Act and Assert: Expect a AnswerAcceptanceException with appropriate message
        thrown.expect(AnswerAcceptanceException.class);
        thrown.expectMessage("Only Questioner can accept this answer as it is their question");

        answerer.acceptAnswer(answer);
    }

    // Original questioner is able to accept an answer
    @Test
    public void originalQuestionerAcceptsAnswer() throws Exception {
        // Act: Questioner accepts the answer
        questioner.acceptAnswer(answer);

        // Assert: Answer is marked as accepted
        assertTrue(answer.isAccepted());
    }

}
