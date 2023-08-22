# SimEtris
A tetris clone

Written for my wife Stefania.

SimEtris is written by Simone Pernice, version 1.4.1, build 9, 29th December 2006.
SimEtris is under GNU GPL 3.0 copyleft license.
Thanks to Federico and Roman for testing and shadow suggestion.
If like it or find a bug write to pernice@libero.it I will fix it! Have Fun!

SimEtris is another clone of Tetris. This version uses vectorial graphics to optimize the image on different display resolutions. 
The goal of the game is to create complete horizontal lines of blocks, which will disappear.
The blocks come in 7 different character shapes: I (red), T (gray), O (light blue), L (yellow), J (fuchsia), S (blue), Z (green). 
They are made by 4 bricks. The blocks fall from the top center of the screen with a random rotation and order. A preview of the next block is showed on the right of the screen and a shadow version of the current block if dropped is visible on the bottom of the screen. 
The game board is composed by cells. You can rotate the blocks (FIRE to rotate right, UP arrow to rotate left) and move them across the screen (RIGHT and LEFT arrows) to drop (DOWN arrow) them in complete lines. 
After every block your score increases by the square of completed lines (if any) times the current level. 
Every time more than xx lines plus current level value are completed you go to the next level. There are yy levels: level 1 takes up to 1 second for every drop step; that time is reduced by a tenth of second for every following level. 
There is also a challenge mode: insert a number between 0 and 99 to get always the same sequence of blocks to challege a friend of yours! 
Enjoy!

