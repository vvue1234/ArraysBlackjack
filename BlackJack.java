//Names: Gabriel Badillo, Jacob Butler, Veejay Vue, Nue Lopez
//January 21, 2025
//Assignment: Update Blackjack Activity

import java.util.Random;
import java.util.Scanner;

public class BlackJack {

    //We can use these in every function without hvaing to pass them in.
    private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" };
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
            "Ace" };
    private static final int[] DECK = new int[52];
    private static int currentCardIndex = 0;

    private static int counterPlayer = 0;
    private static int counterDealer = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Added lines of code
        String answer = "";
        boolean decision = true;

        while (decision) {
            decision = true;

            initializeDeck(); //builds array
            shuffleDeck(); //shuffles the deck 

            int playerTotal = dealInitialPlayerCards();
            int dealerTotal = dealInitialDealerCards();
            

            playerTotal = playerTurn(scanner, playerTotal);

            if (playerTotal > 21) {
                System.out.println("You busted! Dealer wins.");
                counterDealer++;
                System.out.println("Player Wins: " + counterPlayer + " Dealer Wins: " + counterDealer);

                //return;//idk
            }

            dealerTotal = dealerTurn(dealerTotal);
            //Only determines the winner if the player didn't bust
            if (playerTotal <= 21)
                determineWinner(playerTotal, dealerTotal);

    //It repeats while it's still in the game.
            System.out.println("Play again? yes or no ");
            answer = scanner.nextLine().toLowerCase();

            if (answer.equals("no")){
                decision = false;
            }
                //scanner.close();
        }
        System.out.println("Game finished");
        
        System.out.println("Total Player Wins: " + counterPlayer + " Total Dealer Wins: " + counterDealer); //prints out the wins of the player and the dealer at the end of the round

    }

    // this function assigns each card with an I value to be used in a later function
    private static void initializeDeck() {
        for (int i = 0; i < DECK.length; i++) {
            DECK[i] = i;
        }
    }
    //Shuffles deck.
    private static void shuffleDeck() {
        Random random = new Random();
        for (int i = 0; i < DECK.length; i++) {
            int index = random.nextInt(DECK.length);
            int temp = DECK[i];
            DECK[i] = DECK[index];
            DECK[index] = temp;
        }
        System.out.println("printed deck");
        for (int i = 0; i < DECK.length; i++) {
            System.out.print(DECK[i] + " ");
        }
        System.out.println();
    }
    
    // Deals 2 cards for the player
    private static int dealInitialPlayerCards() {
        int card1 = dealCard();
        int card2 = dealCard();
        System.out.println("Your cards: " + RANKS[card1] + " of " + SUITS[DECK[currentCardIndex] % 4] + " and "
                + RANKS[card2] + " of " + SUITS[card2 / 13]);
        return cardValue(card1) + cardValue(card2);
    }
    //This function deals the dealers first card and stores what card it is
    private static int dealInitialDealerCards() {
        int card1 = dealCard();
        System.out.println("Dealer's card: " + RANKS[card1] + " of " + SUITS[DECK[currentCardIndex] % 4]);
        return cardValue(card1);
    }
    
    //This lets the user begin their turn and gives values.
    private static int playerTurn(Scanner scanner, int playerTotal) {  //This brings two arguments... the scanner and another playerTotal.
        while (true) {
            System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?"); 
            String action = scanner.nextLine().toLowerCase();
            if (action.equals("hit")) {
                int newCard = dealCard();
                playerTotal += cardValue(newCard);
                System.out.println("new card index is " + newCard);
                System.out.println("You drew a " + RANKS[newCard] + " of " + SUITS[DECK[currentCardIndex] % 4]);
                if (playerTotal > 21) {
                    break;
                }
            } else if (action.equals("stand")) {
                break;
            } else {
                System.out.println("Invalid action. Please type 'hit' or 'stand'.");
            }
        }
        return playerTotal;
    }

    private static int dealerTurn(int dealerTotal) { 
        while (dealerTotal < 17) {
            int newCard = dealCard();
            dealerTotal += cardValue(newCard);
        }
        System.out.println("Dealer's total is " + dealerTotal); 
        return dealerTotal;
    }

    // Determines the winner of the game
    private static void determineWinner(int playerTotal, int dealerTotal) {
        if (dealerTotal > 21 || playerTotal > dealerTotal) { // If the dealer has more than 21 and player has less than the dealer, then the player wins
            System.out.println("You win!"); 
            counterPlayer++;
            System.out.println("Player Wins: " + counterPlayer + " Dealer Wins: " + counterDealer); // Prints out number of dealer wins and player wins
        } else if (dealerTotal == playerTotal) { // If dealer and player have the same total, then it is a tie
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer wins!"); // If dealer has more, then the dealer wins
            counterDealer++;
            System.out.println("Player Wins: " + counterPlayer + " Dealer Wins: " + counterDealer);
        }
    }
    
    private static int dealCard() {
        return DECK[currentCardIndex++] % 13; //Returns the card's position in the array
    }

    private static int cardValue(int card) { 
        return card < 9 ? card + 2 : 10; // Translate the array position to the card's rank
    }

    int linearSearch(int[] numbers, int key) {
        int i = 0;
        for (i = 0; i < numbers.length; i++) {
            if (numbers[i] == key) {
                return i;
            }
        }
        return -1; // not found
    }
}