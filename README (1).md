# Project 3 - Deliverable 4: Second Prototype + Tests

## Running the Program & its tests

To run the program, once all files have been added, right click on the entire folder "cs2114-project3-group81" or the MainLoop.java file and clik "Run as Java Application".
Each test for the classes will needed to be ran manually - click on each one and click "run as test"

## Reflection

### How did your design absorb the change?

For this deliverable, our group focused on improving how our café ordering system manages orders, using the existing queue operations. We worked on the queue logic and how users interact with it through the console. Priority adding is not working yet.

Classes involved:
- `DrinkOrder.java` - Stores information about each drink order.
- `OrderQueue.java` - Manages the sequence of orders and queue operations.
- `CafeController.java` - Handles user commands and communicates with the model.
- `ConsoleView.java` - Displays menus, order information, and messages to the user.

Our D3 plan predicted that adding order-management behavior would involve the model, controller, and view. What stood out to us was that changes to queue operations also meant checking that the controller and console still behaved consistently. This showed us why it is important to keep each part of the program responsible for its own job.

### Did the change cross the model/view line?

No. Our design keeps the queue and order logic in the model, while the controller handles requests and the view displays the results. The view should not need to know how the deque works internally. This makes the program easier to update and test.

### What changed from our D3 plan?

We decided to keep the project as a console application and focus on its order-management logic and data structures rather than building a graphical interface. This let us work on the main behavior first. We will confirm any additional changes to the original D3 plan against the code before submission.

### What doesn't work yet?

- Priority adding is not working yet. We still need to implement and test how priority orders are inserted into the queue.
- Order cancellation by customer name, including removing an order from the middle of the queue, is not yet implemented.
- Saving order history to a CSV file is not yet implemented.
- The console prompts and formatting could still be improved.

### What design change would make the next feature cheaper? (Exceeds Rubric)

Once priority adding is implemented, we could move its ordering rules into a separate helper class. That would make it easier to add different priority levels or cancellation rules without changing the basic deque operations. It would also make those rules easier to test separately.

## Current Prototype: Order Management

Our prototype is focused on adding and managing café orders using a queue. Priority adding is planned but is not working yet, so we will not demonstrate it as a completed feature.

- `DrinkOrder.java` represents an individual order.
- `OrderQueue.java` manages orders and their sequence.
- `CafeController.java` receives commands and connects the console to the order-management code.
- `ConsoleView.java` shows menus, order details, and feedback without implementing queue logic.

The model, controller, and view each have separate responsibilities, which will help us add priority ordering later without mixing it into the display code.

## Testing and Bad Input Handling

Our JUnit tests should check the model separately from the console interface. Before submission, we need to run the tests and confirm which cases are actually implemented and passing.

### JUnit Test Coverage

| Test scenario | Expected behavior |
| --- | --- |
| Add an order to an empty queue | The order is stored correctly |
| Add multiple orders | The queue maintains the expected order |
| Add a priority order (future test) | Once implemented, the order follows the priority rule |
| Remove an order | The correct order is removed |
| Remove from an empty queue | Empty-queue behavior is handled correctly |
| Peek at an empty queue | Empty-queue behavior is handled correctly |
| Add invalid order information | The input is rejected or handled safely |
| Enter an invalid menu option | The program responds without crashing |

These are test targets, not a claim that every test already passes.

### Bad Input Handling

We want the program to handle invalid menu choices, missing order information, and empty-queue operations without crashing. We still need to verify the actual behavior and test results in Eclipse.

## Next Steps

### Remaining Work for the Final Build

1. **Order cancellation:** Let a customer cancel an order by name, even when it is in the middle of the queue.
3. **CSV export:** Save order history to a CSV file.
4. **Console cleanup:** Improve the readability of menus and order information.

### Stretch Goals

Planned: priority adding, order cancellation by customer name, and CSV order-history export.

Dropped: graphical user interface and networking. We decided to focus on the core console application and data structures.

### 2.5-Minute Demonstration Plan (150 Seconds)

| Time | Demonstration |
| --- | --- |
| 0:00-0:30 | Launch the application and demonstrate empty-queue handling |
| 0:30-1:00 | Add regular orders and show invalid-input handling |
| 1:00-1:45 | Show the current queue order and explain that priority adding is not working yet |
| 1:45-2:30 | Remove or process orders, show the updated queue, and exit |

### Team Responsibilities

| Team member | Planned responsibility |
| --- | --- |
| Hemanth | Order cancellation and demo lead |
| Richard | Queue removal logic and unit tests |
| Talal | ConsoleView prompts and formatting |
| Aidan | CSV export and regression testing |

These are the planned next-step assignments; they do not mean the features are already complete.

## Attribution & GenAI Note

### Attribution Table

| File / Class | Primary Owner(s) | Role & Contribution |
| --- | --- | --- |
| `CafeController.java` | Talal, Aidan | Menu options and order-command handling |
| `CafeControllerTest.java` | Richard | Tests for controller inputs and menu flow |
| `ConsoleView.java` | Richard | Menu display and order information output |
| `Deque.java` | Hemanth | Queue wrapper and snapshot methods |
| `DequeInterface.java` | Hemanth | Interface provided or reviewed |
| `DLinkedDeque.java` | Richard | Doubly linked deque operations |
| `DLinkedDequeTest.java` | Aidan | JUnit tests for deque operations and edge cases |
| `DrinkOrder.java` | Richard | Order information and field validation |
| `EmptyQueueException.java` | Talal | Exception class provided or reviewed |
| `MainLoop.java` | Hemanth | Main application entry point |
| `OrderQueue.java` | Richard | Queue management and order sequencing |
| `OrderQueueTest.java` | Aidan | Tests for queue operations and order sequencing |
| `README.md` | Talal, everyone | Reflection, next steps, and attribution documentation |

Please verify these ownership details and descriptions with the team before submitting.

### Generative AI Usage

We used GenAI to discuss the separation between the model, controller, and view and to help organize and format the README. We also used it to revise documentation after realizing that earlier descriptions included features not present in our code. The group is responsible for checking the final explanations against the Java files and verifying all tests locally. Any additional AI assistance with code or tests should be documented accurately.
