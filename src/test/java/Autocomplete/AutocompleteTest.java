package Autocomplete;


import org.junit.Test;

import java.util.List;

public class AutocompleteTest {
    int correct = 0;
    int total = 0;

    @Test
    public void test() {
        final String dict_file = "src/main/resources/dict.txt";
        final int max = 20;

        Autocomplete<?> ac = new AutocompleteRecent(dict_file, max);
        testAutocomplete(ac);
    }

    private void testAutocomplete(Autocomplete<?> ac) {
        String prefix;
        List<String> expected;
        List<String> result;

        prefix = "";
        expected = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "sheep");

        prefix = "     ";
        expected = List.of("sheep", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, " ");

        prefix = "   ";
        expected = List.of("", "sheep", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "dictionaryquiz");

        prefix = " dictionary";
        expected = List.of("dictionary", "dictionaryquiz");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "");

        prefix = "apple";
        expected = List.of("apple", "appled", "apples", "applenut", "applecart", "applejack", "applejohn", "appleroot",
                "applewife", "applewood", "appleberry", "appledrane", "appledrone", "appleringy", "applesauce",
                "applesnits", "applewoman", "applegrower", "applemonger", "appleringie");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "applejohn");

        prefix = "apple";
        expected = List.of("applejohn", "apple", "appled", "apples", "applenut", "applecart", "applejack", "appleroot",
                "applewife", "applewood", "appleberry", "appledrane", "appledrone", "appleringy", "applesauce",
                "applesnits", "applewoman", "applegrower", "applemonger", "appleringie");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "applejohn");

        prefix = "apple";
        expected = List.of("applejohn", "apple", "appled", "apples", "applenut", "applecart", "applejack", "appleroot",
                "applewife", "applewood", "appleberry", "appledrane", "appledrone", "appleringy", "applesauce",
                "applesnits", "applewoman", "applegrower", "applemonger", "appleringie");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "applejohn");

        prefix = "apple";
        expected = List.of("applejohn", "apple", "appled", "apples", "applenut", "applecart", "applejack", "appleroot",
                "applewife", "applewood", "appleberry", "appledrane", "appledrone", "appleringy", "applesauce",
                "applesnits", "applewoman", "applegrower", "applemonger", "appleringie");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "appleroot");

        prefix = "apple";
        expected = List.of("appleroot", "applejohn", "apple", "appled", "apples", "applenut", "applecart", "applejack",
                "applewife", "applewood", "appleberry", "appledrane", "appledrone", "appleringy", "applesauce",
                "applesnits", "applewoman", "applegrower", "applemonger", "appleringie");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "orange");

        prefix = "apple";
        expected = List.of("orange", "appleroot", "applejohn", "apple", "appled", "apples", "applenut", "applecart",
                "applejack", "applewife", "applewood", "appleberry", "appledrane", "appledrone", "appleringy",
                "applesauce", "applesnits", "applewoman", "applegrower", "applemonger");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "orange");

        prefix = "apple";
        expected = List.of("orange", "appleroot", "applejohn", "apple", "appled", "apples", "applenut", "applecart",
                "applejack", "applewife", "applewood", "appleberry", "appledrane", "appledrone", "appleringy",
                "applesauce", "applesnits", "applewoman", "applegrower", "applemonger");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "neitherorangenorapple");

        prefix = "apple";
        expected = List.of("neitherorangenorapple", "orange", "appleroot", "applejohn", "apple", "appled", "apples",
                "applenut", "applecart", "applejack", "applewife", "applewood", "appleberry", "appledrane",
                "appledrone", "appleringy", "applesauce", "applesnits", "applewoman", "applegrower");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "neitherorangenorapple  ");

        prefix = "apple";
        expected = List.of("neitherorangenorapple", "orange", "appleroot", "applejohn", "apple", "appled", "apples",
                "applenut", "applecart", "applejack", "applewife", "applewood", "appleberry", "appledrane",
                "appledrone", "appleringy", "applesauce", "applesnits", "applewoman", "applegrower");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "   neitherorangenorapple  ");

        prefix = "apple";
        expected = List.of("neitherorangenorapple", "orange", "appleroot", "applejohn", "apple", "appled", "apples",
                "applenut", "applecart", "applejack", "applewife", "applewood", "appleberry", "appledrane",
                "appledrone", "appleringy", "applesauce", "applesnits", "applewoman", "applegrower");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "");

        prefix = "thisisnotaword";
        expected = List.of();
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "test");

        prefix = "thisisnota";
        expected = List.of();
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "thisisnotaword");

        prefix = "thisisnotaword";
        expected = List.of("test", "thisisnotaword");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "test");

        prefix = "affirm";
        expected = List.of("affirm", "affirms", "affirmed", "affirmer", "affirmly", "affirmant", "affirmers",
                "affirming", "affirmable", "affirmably", "affirmance", "affirmation", "affirmative", "affirmatory",
                "affirmingly", "affirmations", "affirmatives", "affirmatively", "affirmativeness");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "reaffirm");

        prefix = "affirm";
        expected = List.of("reaffirm", "affirm", "affirms", "affirmed", "affirmer", "affirmly", "affirmant",
                "affirmers", "affirming", "affirmable", "affirmably", "affirmance", "affirmation", "affirmative",
                "affirmatory", "affirmingly", "affirmations", "affirmatives", "affirmatively", "affirmativeness");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "reaffirm");

        prefix = "affirm";
        expected = List.of("reaffirm", "affirm", "affirms", "affirmed", "affirmer", "affirmly", "affirmant",
                "affirmers", "affirming", "affirmable", "affirmably", "affirmance", "affirmation", "affirmative",
                "affirmatory", "affirmingly", "affirmations", "affirmatives", "affirmatively", "affirmativeness");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "rereaffirm");

        prefix = "affirm";
        expected = List.of("rereaffirm", "reaffirm", "affirm", "affirms", "affirmed", "affirmer", "affirmly",
                "affirmant", "affirmers", "affirming", "affirmable", "affirmably", "affirmance", "affirmation",
                "affirmative", "affirmatory", "affirmingly", "affirmations", "affirmatives", "affirmatively");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "affirm");

        prefix = "affirm";
        expected = List.of("affirm", "rereaffirm", "reaffirm", "affirms", "affirmed", "affirmer", "affirmly",
                "affirmant", "affirmers", "affirming", "affirmable", "affirmably", "affirmance", "affirmation",
                "affirmative", "affirmatory", "affirmingly", "affirmations", "affirmatives", "affirmatively");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "affirm");

        prefix = "affirm";
        expected = List.of("affirm", "rereaffirm", "reaffirm", "affirms", "affirmed", "affirmer", "affirmly",
                "affirmant", "affirmers", "affirming", "affirmable", "affirmably", "affirmance", "affirmation",
                "affirmative", "affirmatory", "affirmingly", "affirmations", "affirmatives", "affirmatively");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "affirmatively");

        prefix = "affirm";
        expected = List.of("affirmatively", "affirm", "rereaffirm", "reaffirm", "affirms", "affirmed", "affirmer", "affirmly",
                "affirmant", "affirmers", "affirming", "affirmable", "affirmably", "affirmance", "affirmation",
                "affirmative", "affirmatory", "affirmingly", "affirmations", "affirmatives");
        result = ac.getCandidates(prefix);
        testGetCandidates(result, prefix, expected);
        ac.pickCandidate(prefix, "affirmatively");


        System.out.printf("Score: %d/%d\n", correct, total);
    }

    private void testGetCandidates(List<String> result, String prefix, List<String> expected) {
        total++;
        System.out.println("TEST " + total + ": ");
//        System.out.println(result.toString());
//        System.out.println(expected.toString());
        if (result.equals(expected)) {
            System.out.println("TRUE!");
            correct++;
        } else System.out.println("FALSE");
    }

}