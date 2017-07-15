
package ua.nure.sereda.SummaryTask3;

import java.util.Collections;
import java.util.Comparator;

import ua.nure.sereda.SummaryTask3.cards.Cards;
import ua.nure.sereda.SummaryTask3.cards.OldCard;


public class Sorter {


    private static final Comparator<OldCard> SORT_CARDS_BY_TYPE_NAME = new Comparator<OldCard>() {
        @Override
        public int compare(OldCard card1, OldCard card2) {
            return card1.getType().getTypeName().compareTo(card2.getType().getTypeName());
        }
    };

    /**
     * Sorts cards by year
     */
    private static final Comparator<OldCard> SORT_CARDS_BY_YEAR = new Comparator<OldCard>() {
        @Override
        public int compare(OldCard card1, OldCard card2) {
            return card1.getYear().getYear() - card2.getYear().getYear();
        }
    };

    /**
     * Sorts cards by valuable
     */
    private static final Comparator<OldCard> SORT_CARDS_BY_VALUABLE = new Comparator<OldCard>() {
        @Override
        public int compare(OldCard card1, OldCard card2) {
            return card1.getValuable().compareTo(card2.getValuable());
        }
    };

    /**
     * Sorts cards by send attribute
     */
    private static final Comparator<OldCard> SORT_CARDS_BY_SEND = new Comparator<OldCard>() {
        @Override
        public int compare(OldCard card1, OldCard card2) {
            if (card1.getType().isSend() && !card2.getType().isSend()) {
                return -1;
            }
            if (card2.getType().isSend() && !card1.getType().isSend()) {
                return 1;
            }
            return 0;
        }
    };


    /**
     * Sorts cards by send attribute
     *
     * @param cards to be sorted
     */
    static void setSortCardsBySend(Cards cards) {
        Collections.sort(cards.getOldCard(), SORT_CARDS_BY_SEND);
    }

    /**
     * Sorts cards by valuable
     *
     * @param cards to be sorted
     */
    static void setSortCardsByValuable(Cards cards) {
        Collections.sort(cards.getOldCard(), SORT_CARDS_BY_VALUABLE);
    }

    /**
     * Sorts cards by year
     *
     * @param cards to be sorted
     */
    static void setSortCardsByYear(Cards cards) {
        Collections.sort(cards.getOldCard(), SORT_CARDS_BY_YEAR);
    }

    /**
     * Sorts cards by type name
     *
     * @param cards to be sorted
     */
    static void setSortCardsByTypeName(Cards cards) {
        Collections.sort(cards.getOldCard(), SORT_CARDS_BY_TYPE_NAME);
    }
}

