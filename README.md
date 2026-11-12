Run Instructions: 

To run the program, simply press the run button on the MainLoop.java file.


Reflection

We did not really change anything from our spec document. 

Instead we simply chose to start with a simpler implementation of our idea as a proof of concept before making the full product for the next prototype.

As a result, features such as the priority system are not present in this version.

The model does, in its current state, stand alone from the view because the view only pulls information from the model and 
displays it.

There is currently an issue with our implementation of the Deque that must be smoothed out before the program works correctly.


Next Steps

Of course, we will add the priority system and make the Deque work both ways, as a Deque should. In terms of specific methods being added to the Deque, it will be addToFront, removeBack, getBack, isEmpty, and clear. 

We will add the LinkedStack class so that the priority adding system can work correctly. In addition to the methods above, our Deque class will have a generalized "add" method that adds to the front or back based on the Object's priority. This is because our Deque implementation needs to be different from what is provided anyway.
push // adds a priority drink order to the bottom of the stack // Controller will handle bad input
pop // will return the oldest priority order from the top of the stack // Emptyqueue class will throw the empty queue error on an empty queue
peek // Returns the oldest priority drink without removing it, this will allow the user to see what priority drink needs to be completed next.

We will then begin working on some of our stretch goals, such as the Rush Hour feature.


Attribution

Hemanth: DrinkOrder class and MainLoop class

Richard: Deque Implementation/Deque Test Class

Talal: Attribution Table

Aidan: README Review

Group Gen AI Transcript:
The group largely did not use AI and instead opted to use precious class material as a refrence when implementing deque logic & syntax. What the group plans to do is utilize AI later in the project; mainly to cover weak aspects, ensure our tests have good coverage and overall strengthen the integrity of our code.
