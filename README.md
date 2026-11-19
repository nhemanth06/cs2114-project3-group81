Run Instructions: 

To run the program, simply press the run button on the MainLoop.java file.


Reflection

We did not really change anything from our spec document. 

Instead we simply chose to start with a simpler implementation of our idea as a proof of concept before making the full product for the next prototype.

As a result, features such as the priority system are not present in this version.

The model does, in its current state, stand alone from the view because the view only pulls information from the model and 
displays it.

There is currently an issue with our implementation of the Deque that must be smoothed out before the program works correctly.


What doesn't work yet?

Working and tested: All five MVP commands (add order, view next, complete next, view completed, view counts) run through CafeController without crashing. The bad-input cases from our scope are handled with an error message and a re-prompt: non-numeric or out-of-range menu choices, a blank name, a name over 50 characters, a drink number not on the menu, a size other than S/M/L, a priority answer other than y/n, and viewing or completing an order when the line is empty. All 40 JUnit tests (DLinkedDequeTest, OrderQueueTest, CafeControllerTest) pass.

Not working or missing:

- Waiting-time tracker (our D3 change feature) is not implemented yet. Drinks have no preparation times, OrderQueue does not calculate estimated waits, and there is no menu command to show them.

- Priority orders are served newest-first. A priority order is added to the very front, so a later priority customer is served before an earlier one. Our D3 plan was to keep priority customers in arrival order using a LinkedStack, which has not been built. The test laterPriorityOrderIsServedBeforeEarlierPriorityOrder currently checks for the newest-first behavior.

- No JUnit tests for DrinkOrder. Its validation (checkName, checkDrink, checkSize) works in the console but is not covered by tests.

- Rush-hour mode (stretch goal) has not been started.

- Minor: "Cappuccino" is misspelled as "Cappucino" on the drink menu, and DrinkOrder's fields are not final.

Next Steps

Of course, we will add the priority system and make the Deque work both ways, as a Deque should. In terms of specific methods being added to the Deque, it will be addToFront, removeBack, getBack, isEmpty, and clear. 

We will add the LinkedStack class so that the priority adding system can work correctly. In addition to the methods above, our Deque class will have a generalized "add" method that adds to the front or back based on the Object's priority. This is because our Deque implementation needs to be different from what is provided anyway.
push // adds a priority drink order to the bottom of the stack // Controller will handle bad input
pop // will return the oldest priority order from the top of the stack // Emptyqueue class will throw the empty queue error on an empty queue
peek // Returns the oldest priority drink without removing it, this will allow the user to see what priority drink needs to be completed next.

After this, we will complete the test classes for both data structures and our DrinkOrder object class.

We will then begin working on some of our stretch goals, such as the Rush Hour feature.

As far as owners for each part, Hemanth will finish up the Deque implementation and update the main loop so that it works with all of the new method. Hemanth will also write a test class for DrinkOrder. Richard will add the LinkedStackADT to set up the framework for the priority system. Talal will begin working on creating a menu of allowed orders, and Aidan will work on the Rush Hour stretch goal feature.


Attribution

Hemanth: DrinkOrder class and MainLoop class

Richard: Deque Implementation/Deque Test Class

Talal: Attribution Table

Aidan: README Review

Group Gen AI Transcript:
The group largely did not use AI and instead opted to use precious class material as a refrence when implementing deque logic & syntax. What the group plans to do is utilize AI later in the project; mainly to cover weak aspects, ensure our tests have good coverage and overall strengthen the integrity of our code.
