# ConsoleChessBinaryFile
GUI Project II

10 IV 2023

A chess game is a family of strategic board games played by two players on a square chessboard of 64 squares, using a set of pawns and pieces.

Implement a fully functional chess game using the object-oriented programming con- cepts presented during the classes. Divide the program into logical and visual parts. In the logical part, implement all the issues related to the actors on the board (pawns and pieces), while in the visual part, implement a text representation of the board with pawns and pieces.

Here are the steps to follow:

- Start by designing the structure of classes and interfaces. Focus on chess game features such as move mechanics, mechanisms for checking whether a given move is allowed or not, check and checkmate detection mechanisms, and general game logic. Also define a class representing the game board and classes representing individual chess pieces.
- Create interfaces to define general behaviors for different classes.
- Create abstract classes to define general properties for several types of pieces.
- Implement game logic, such as mechanisms to check if a move is allowed or not, mechanisms to detect check and checkmate, as well as player logic. You can also use lambda expressions to define anonymous functions, e.g., to search the board for pieces.
- Implement the visual part that allows displaying the board with pawns and pieces.

Make sure that there is the possibility to save and load the game board at any point of the game, stored as a binary file according to the specified format.

Specification of the format starting from the least significant bit (bit at index 0):

- The first 3 bits determine the type of piece or pawn (1 - king, 2 - queen, 3 - rook, 4 - bishop, 5 - knight, 0 - pawn);
- The next 4 bits specify the horizontal position;
- The next 4 bits specify the vertical position;
- The last bit specifies the color of the piece.

Note that position 0,0 is reserved for a piece that is not on the board.

Use all the programming techniques presented during lectures and exercises (inter-faces, abstract classes, classes, lambda expressions, etc.).
