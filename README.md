# Stack Overboard: Unit Testing for User Model

## Project Overview

**Stack Overboard**, a project designed to replicate the functionality of a Q&A bulletin board system similar to Stack Overflow. Users can ask questions, provide answers, and interact through upvotes and downvotes while earning reputation points based on their contributions. The project centers around a model representing these interactions, with a primary focus on the **User model**.

This project emphasizes creating **unit tests** to validate critical behaviors in the **User model**, ensuring that the reputation and voting systems function as expected. My role is to test these behaviors and ensure the application logic remains robust and reliable.

This project was created as part of Unit 3 of my Java Web Development Techdegree at Treehouse.

---

## Objective

The main goal of this project is to write **unit tests** for the **User model** to validate the following functionalities:

1. **Reputation System**:
   - Question upvotes increase the questioner's reputation by 5 points.
   - Answer upvotes increase the answerer's reputation by 10 points.
   - Accepted answers give the answerer a 15-point boost.
   - Downvotes on answers reduce the answerer's reputation by 1 point.

2. **Voting Restrictions**:
   - Users cannot upvote or downvote their own questions or answers.

3. **Answer Acceptance**:
   - Only the author of a question can accept an answer.
   - Non-authors attempting to accept an answer receive an appropriate exception.
---

## Features Tested

### Reputation System
Comprehensive tests validate that reputation changes occur under the following conditions:
- **Question Upvotes**: +5 points to the questioner.
- **Answer Upvotes**: +10 points to the answerer.
- **Accepted Answer**: +15 points to the answerer.
- **Answer Downvotes**: -1 point to the answerer.

### Voting Restrictions
Tests ensure that:
- Users cannot upvote or downvote their own posts (questions or answers).
- Violations throw a `VotingException` with an appropriate message.

### Answer Acceptance
Tests confirm that:
- Only the question author can accept an answer.
- Attempting to accept an answer as a non-author throws an `AnswerAcceptanceException`.

---

## Technologies Used

- **Java**: Core programming language.
- **JUnit 4**: Testing framework used to write and execute unit tests.

---

## Instructions for Running Tests

1. **Clone the Repository**:
   ```
   git clone https://github.com/apatino16/stackOverflowModelUnitTests.git
   ```

2. **Ensure Dependencies Are Installed**:
   - The project uses JUnit 4. Ensure it's properly configured in your IDE or build tool (e.g., IntelliJ IDEA).

3. **Run Tests**:
   - If using IntelliJ IDEA:
     - Right-click on the `src/test` folder or `UserTest` class and select `Run Tests`.
   - If using the command line:
     ```
     ./gradlew test
     ```
     
4. **Review Results**:
   - All tests should pass, confirming that the **User model** behaves as expected.

---

## File Structure

```
src
├── main
│   └── java
│       └── com.teamtreehouse.techdegree.overboard.model
│           ├── User.java
│           ├── Question.java
│           ├── Answer.java
│           ├── Board.java
│           └── Post.java
├── test
│   └── java
│       └── com.teamtreehouse.techdegree.overboard.model
│           └── UserTest.java
```

---

## Contribution and Acknowledgment
My contribution to this project was exclusively focused on writing unit tests for the User model. This work ensures the system's core functionalities behave as expected under a variety of scenarios. I would like to acknowledge the TreeHouse team for creating the model code that provided the foundation for these tests.
