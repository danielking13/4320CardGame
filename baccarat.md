# Baccarat
Baccarat is a card game that uses 8 decks. There are bets and the goal of Baccarat is to choose the correct hand, whether it is the player or the banker.

## Bets
* Player Bet: If the player's hand wins, all bets on the player win even money.
* Banker Bet: If the banker's hand wins, all bets on the banker pay 19 to 20.
* Tie: In the event of a tie, all bets push. (Push means return of original bet)

## Gameplay
To start the hand, two cards are selected for each side. The goal of a hand is to get as close to 9 as possible. If the player or the banker has a better hand, (i.e. closer to 9),
then the better hand wins and either the player or banker bet is paid out. If it is a tie, the bet is pushed. Each card has a point value. Aces are 1 point, 2-9 are 2-9 points, and tens
face cards are worth zero points. Points are added, but if a player goes over 9, the first digit is removed. For example, a 9 and a 4 would give 13, but after the one is dropped, a score of 3
would remain. The player and banker are dealt two cards and may or may not be dealt a third depending upon the following rules.

* If either the player or banker is dealt an 8 or 9, both hands stand.
* If the player's total is 5 or less, the player will draw a card.
* If the player's total is 6 or 7, the player will stand.
* If the player's hand stays with 6 or 7 points, the banker will draw if it has 5 points or less. Otherwise, the banker will stand.
* If the player does draw a third card, the banker will draw a card dependent upon the value of the player's third card and the bankers own score.
* The scores are compared and the higher score wins.

### Development challenges
* Simulate a deck of cards and deal with shuffling. Since the game is computerized, a single deck may suffice with shuffling after every hand.
* Implement a betting pile of the "chips." Since this is not actual gambling, there should be a way to get more chips as well as keep track of the current amount of chips.
* Implement the rules above along with the respective payouts.
* Convert the game from the visual interface of a deck of cards to a voice based layout. Since the only thing that matters is the score, this may be less complicated.
