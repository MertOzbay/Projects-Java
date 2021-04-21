
package Autocomplete;


import java.util.*;
import java.util.stream.Stream;


public class AutocompleteRecent extends Autocomplete<List<String>> {
    public AutocompleteRecent(String dict_file, int max) {
        super(dict_file, max);
    }

    @Override
    public List<String> getCandidates(String prefix) {
        prefix = prefix.trim();

        TrieNode<List<String>> node = find (prefix);
        ArrayList<String> result = new ArrayList();

        if (node != null) {

            if (node.getValue() != null) {
                for (String word : node.getValue()) {
                    result.add(word);
                    if (result.size() >= getMax()) return result;
                }
            }

            if (node.isEndState()) {
                if (!result.contains(prefix)) result.add(prefix);
                if (result.size() >= getMax()) return result;
            }

            Map<Character, TrieNode<List<String>>> children = node.getChildrenMap();

            Stream chars = children.keySet().stream().sorted();

            Deque<TrieNode> traversal = new ArrayDeque();
            Deque<String> prefixTraversal = new ArrayDeque();
            Iterator<Character> iter = chars.iterator();

            while (iter.hasNext()) {
                char current = iter.next();
                traversal.addLast(node.getChild(current));
                prefixTraversal.addLast(prefix + current);
            }

            while (!traversal.isEmpty()) {
                getCandidatesAux(traversal.poll(), traversal, prefixTraversal.poll(), prefixTraversal, result);
                if (result.size() >= getMax()) return result;
            }
        }

        return result;
    }

    private void getCandidatesAux(TrieNode node, Deque traversal, String prefix, Deque prefixTraversal, List result){

        if (node.isEndState()) {
                if (!result.contains(prefix)) result.add(prefix);
                if (result.size()>=getMax()) return;
            }

        Map<Character, TrieNode<List<String>>> children = node.getChildrenMap();
        Stream chars = children.keySet().stream().sorted();
        Iterator<Character> iter = chars.iterator();

        while (iter.hasNext()){
            char current = iter.next();
            traversal.addLast(node.getChild(current));
            prefixTraversal.addLast(prefix+current);
        }
    }


    @Override
    public void pickCandidate(String prefix, String candidate) {
        prefix = prefix.trim();
        candidate = candidate.trim();

        TrieNode<List<String>> node = find (prefix);

        if (node == null){
            put(prefix, null);
            node = find (prefix);
            node.setEndState(false);
        }

        List candidateList = node.getValue();

        if (candidateList == null){
            node.setValue(new ArrayList<String>());
            candidateList = node.getValue();
        }
        if (candidateList.contains(candidate)) candidateList.remove(candidate);
        candidateList.add(0,candidate);


        node = find(candidate);
        if (node == null){
            put(candidate, null);
        } else node.setEndState(true);

    }
}